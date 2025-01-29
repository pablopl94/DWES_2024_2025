package cajeroweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cajeroweb.modelo.dao.CuentaDao;
import cajeroweb.modelo.dao.MovimientoDao;
import cajeroweb.modelo.entidades.Cuenta;
import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {

    @Autowired
    private CuentaDao cdao;

    @Autowired
    private MovimientoDao mdao;

    /**
     * Página principal:
     * Si no hay sesión activa, redirige al login.
     * Si hay sesión activa, muestra la vista home.
     */
    @GetMapping("/home")
    public String inicio(HttpSession sesion) {
        Cuenta cuenta = (Cuenta) sesion.getAttribute("cuenta");
        if (cuenta == null) {
            return "redirect:/login";
        }
        return "Home";
    }

    /**
     * Muestra el formulario de login.
     * - Devuelve la vista "FormLogin" para puedas ingresar su ID de cuenta.
     */
    @GetMapping({"", "/", "/login"})
    public String mostrarFormLogin() {
        return "FormLogin";
    }

    /**
     * Procesa el formulario de login:
     * - Recibe el ID de cuenta ingresado por el usuario.
     * - Verifica si la cuenta existe en la base de datos.
     * - Si la cuenta no existe, redirige al formulario de login con un mensaje de error.
     * - Si la cuenta es válida, la guarda en la sesión y redirige a la página principal.
     */
    @PostMapping("/login")
    public String procesarFormLogin(
        @RequestParam int idCuenta,////Recibe el ID de la cuenta ingresada.
        HttpSession sesion,//Permite guardar y recuperar información sobre la cuenta activa durante la sesión.
        RedirectAttributes ratt //Utilizado para enviar mensajes a la vista tras redirigir.
    ) {
        Cuenta cuenta = cdao.findById(idCuenta);
        if (cuenta == null) {
            ratt.addFlashAttribute("error", "El número de cuenta no existe.");
            return "redirect:/login";
        }
        sesion.setAttribute("cuenta", cuenta);
        return "redirect:/home";
    }

    /**
     * Cierra la sesión actual:
     * - Elimina explícitamente el atributo "cuenta" de la sesión.
     * - Invalida la sesión para asegurarse de que no quede información.
     * - Redirige a la raíz utilizando "forward".
     */
    @GetMapping("/logout")
	public String cerrarSesion(HttpSession sesion) {
		sesion.removeAttribute("cuenta");
		sesion.invalidate();
		return "forward:/";
	}

    /**
     * Maneja el ingreso de dinero en la cuenta:
     * - Obtiene la cuenta activa desde la sesión.
     * - Si es válida, actualiza el saldo de la cuenta y registra el ingreso como un movimiento.
     * - Redirige al inicio con un mensaje de éxito.
     */

    @PostMapping("/ingresar")
    public String ingresarDinero(
        @RequestParam double cantidad, //recibe la cantidad ingresada.
        HttpSession sesion, //Permite acceder a la cuenta activa almacenada en la sesión.
        RedirectAttributes ratt //Utilizado para enviar mensajes a la vista tras redirigir.
    ) {
        Cuenta cuenta = obtenerCuentaSesion(sesion);

            cdao.modificarCuenta(cuenta, cantidad, true);
            mdao.registrarMovimiento(cuenta, cantidad, "INGRESO");
            ratt.addFlashAttribute("mensaje", "Ingreso exitoso de " + cantidad + " euros.");

        return "redirect:/home";
    }

    /**
     * Maneja el retiro de dinero de la cuenta:
     * - Obtiene la cuenta activa desde la sesión.
     * - Verifica que la cantidad ingresada no supere el saldo disponible.
     * - Si hay suficiente saldo, actualiza el saldo de la cuenta y registra el retiro como un movimiento.
     * - Redirige al inicio con un mensaje de éxito o error según el caso.
     */

    @PostMapping("/retirar")
    public String retirarDinero(
        @RequestParam double cantidad, //recibe la cantidad ingresada.
        HttpSession sesion, //Permite acceder a la cuenta activa almacenada en la sesión.
        RedirectAttributes ratt //Utilizado para enviar mensajes a la vista tras redirigir.
    ) {
        Cuenta cuenta = obtenerCuentaSesion(sesion);

        if (cuenta.getSaldo() < cantidad) {
            ratt.addFlashAttribute("error", "SALDO INSUFICIENTE");
        } else {
            cdao.modificarCuenta(cuenta, cantidad, false);
            mdao.registrarMovimiento(cuenta,-cantidad, "RETIRO");
            ratt.addFlashAttribute("mensaje", "Retiro exitoso de " + cantidad + " euros.");
        }

        return "redirect:/home";
    }

    /**
     * Muestra los movimientos de la cuenta activa:
     * - Obtiene la cuenta activa desde la sesión.
     * - Recupera los movimientos asociados a esa cuenta desde la base de datos.
     * - Agrega los movimientos al modelo para mostrarlos en la vista "Listamovimientos".
     */

    @GetMapping("/movimientos")
    public String verMovimientos(
    		HttpSession sesion, 
    		Model model //permite pasar datos desde el controlador a la vista.
    ) {
        Cuenta cuenta = obtenerCuentaSesion(sesion);
        model.addAttribute("movimientos", mdao.buscarMovimientoPorCuenta(cuenta));
        return "ListaMovimientos";
    }
    
    // TRANSFERENCIA ENTRE CUENTAS
    @PostMapping("/transferencia")
    public String procesarTransferencia(
        @RequestParam int idCuentaDestino,  // Recibe el ID de la cuenta de destino.
        @RequestParam double cantidad,     // Recibe la cantidad a transferir.
        HttpSession sesion,                // Permite acceder a la sesión activa.
        RedirectAttributes ratt            // Utilizado para pasar mensajes a la vista tras la redirección.
    ) {
        // Obtener la cuenta de origen desde la sesión
        Cuenta cuentaOrigen = obtenerCuentaSesion(sesion);
        
        // Validación: Verifica que la cuenta de origen esté activa
        if (cuentaOrigen == null) {
            ratt.addFlashAttribute("error", "No hay cuenta activa en la sesión.");
            return "redirect:/login";  // Redirige al login si no hay cuenta activa.
        }

        // Validación: Verifica que las cuentas de origen y destino no sean la misma.
        if (cuentaOrigen.getIdCuenta() == idCuentaDestino) {
            ratt.addFlashAttribute("error", "No se puede transferir a la misma cuenta.");
            return "redirect:/home";  // Redirige de nuevo al formulario de transferencia.
        }

        // Validación: Verifica que la cuenta de origen tenga suficiente saldo.
        if (cuentaOrigen.getSaldo() < cantidad) {
            ratt.addFlashAttribute("error", "SALDO INSUFICIENTE");
            return "redirect:/home";  // Redirige de nuevo al formulario de transferencia.
        }


        
        // Modificamos la cantidad de la cuenta de origen como una retirada
        cdao.modificarCuenta(cuentaOrigen, cantidad, false);
        
        // Modificamos la cantidad de la cuenta de origen como un ingreso
        Cuenta cuentaDestino = cdao.findById(idCuentaDestino); // Buscamos la id de cuenta a la que vamos a transferir
        cdao.modificarCuenta(cuentaDestino, cantidad, true);
        
        
        
        
        // Registrar el movimiento de la cuenta de origen
        mdao.registrarMovimiento(cuentaOrigen, -cantidad, "EXTRACTO POR TRANSFERENCIA");
        
        // Registrar el movimiento de la cuenta de destino
        mdao.registrarMovimiento(cuentaDestino, cantidad, "INGRESO POR TRANSFERENCIA");

        // Agregar un mensaje de éxito
        ratt.addFlashAttribute("mensaje", "Transferencia realizada con éxito de " + cantidad + " euros.");
        return "redirect:/home";  // Redirige al usuario a la página principal tras la transferencia exitosa.
    }


    /**
     * Método auxiliar para obtener la cuenta activa desde la sesión:
     * - Centraliza el acceso al atributo "cuenta" almacenado en la sesión.
     * - Facilita la reutilización y reduce redundancias en el código de los métodos principales.
     * - Devuelve la cuenta activa
     */

    private Cuenta obtenerCuentaSesion(HttpSession sesion) {
        return (Cuenta) sesion.getAttribute("cuenta");
    }
}
