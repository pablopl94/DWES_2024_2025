package restproductos.modelo.entities;

import java.io.Serializable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


/**
 * The persistent class for the usuarios database table.
 * 
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="usuarios")
public class Usuario implements UserDetails, Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String username;
	private String password;
	private String nombre;
	private String apellidos;
	private String direccion;
	private int enabled;
	@Temporal(TemporalType.DATE)
	@Column(name="FECHA_REGISTRO")
	private Date fechaRegistro;
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
		name="usuario_perfiles"
		, joinColumns={
			@JoinColumn(name="USERNAME")
			}
		, inverseJoinColumns={
			@JoinColumn(name="ID_PERFIL")
			}
		)
	private List<Perfil> perfiles;
	@Override
	public String toString() {
		return "Usuario [username=" + username + ", apellidos=" + apellidos + ", direccion=" + direccion + ", enabled="
				+ enabled + ", fechaRegistro=" + fechaRegistro + ", nombre=" + nombre + ", password=" + password
				 + "]";
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return perfiles.stream()
				.map(p-> new SimpleGrantedAuthority(p.getNombre()))
				.toList();
	}

}