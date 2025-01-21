package familias.modelo.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
@Table(name="familias")
public class Familia {

	@EqualsAndHashCode.Include
	@Id
	@Column(name="id_familia")
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int idFamilia;
	private String nombre;

}
