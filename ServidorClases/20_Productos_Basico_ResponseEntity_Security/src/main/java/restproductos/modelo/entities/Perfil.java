package restproductos.modelo.entities;

import java.io.Serializable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;



/**
 * The persistent class for the perfiles database table.
 * 
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="perfiles")
public class Perfil implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_PERFIL")
	private int idPerfil;

	private String nombre;



}