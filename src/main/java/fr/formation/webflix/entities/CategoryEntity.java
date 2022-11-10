package fr.formation.webflix.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "category")
public class CategoryEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Pattern(regexp = "\\w{2,100}", flags = {Pattern.Flag.CASE_INSENSITIVE, Pattern.Flag.DOTALL})
	@Column(nullable = false, length = 100, unique = true)
	private String name;

	@OneToMany(mappedBy = "category")
	private Collection<VideoEntity> videos;
}
