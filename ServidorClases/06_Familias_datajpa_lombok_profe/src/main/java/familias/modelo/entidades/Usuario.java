package familias.modelo.entidades;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name="usuarios")
public class Usuario implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@EqualsAndHashCode.Include
	private String username;
	private String password;
	private String nombre;
	private String apellidos;
	private String email;
	@Column(name="created_at")
	@Temporal(TemporalType.DATE)
	private Date createdAt;
	private int enabled;

}
