package familias.modelo.entidades;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "usuarios")
public class Usuario {
	
	@Id
	@EqualsAndHashCode.Include
    private String username;
	
    private String password;
    private String nombre;
    private String apellidos;
    private String email;
    @Column(name="create_at")
    private LocalDate createAt;
    private int enabled;


}
