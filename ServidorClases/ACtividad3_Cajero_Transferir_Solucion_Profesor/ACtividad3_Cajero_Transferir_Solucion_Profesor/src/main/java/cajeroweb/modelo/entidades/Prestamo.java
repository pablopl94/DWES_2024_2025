package cajeroweb.modelo.entidades;

import java.io.Serializable;
import java.time.LocalDate;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "prestamos")
public class Prestamo implements Serializable {

    private static final long serialVersionUID = 1L;

    // Clave primaria
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_prestamo")
    @EqualsAndHashCode.Include
    private int idPrestamo;

    // Otros atributos
    @Column(nullable = false)
    private String descripcion;

    @Column(name = "cantidad_prestamo")
    private double cantidadPrestamo;

    @Column(name = "fecha_prestamo")
    private LocalDate fechaPrestamo;

    @Column(name = "tasa_interes_anual")
    private double tasaInteresAnual;

    @Column(name = "plazo_meses")
    private int plazoMeses;

    @Column(name = "tipo_cuota")
    private String tipoCuota;

    // Relación con Cuenta
    @ManyToOne
    @JoinColumn(name = "id_cuenta")
    private Cuenta cuenta;
}
