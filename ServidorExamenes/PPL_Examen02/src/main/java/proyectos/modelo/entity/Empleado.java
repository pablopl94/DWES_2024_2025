package proyectos.modelo.entity;

import java.io.Serializable;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name="empleados")
public class Empleado implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_empl")
	private int idEmpl;
	
	private String nombre;
	private String apellidos;
	private String email;
	private String password;
	private double salario;
	
	@Column(name="fecha_ingreso")
	private LocalDate fechaIngreso;
	
	@Column(name="fecha_nacimiento")
	private LocalDate fechaNacimiento;
	
	@ManyToOne
	@JoinColumn(name="id_perfil")
	private Perfil perfil;
	
	@ManyToOne
	@JoinColumn(name="id_depar")
	private Departamento departamento;

}
