package fr.formation.webflix.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "profile")
public class ProfileEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(length = 20, nullable = false)
	private String name;

	@Column(length = 10, nullable = false)
	private String language;

	@Column(nullable = false)
	private String photo;

	@ManyToOne(optional = false)
	private UserEntity user;

	@OneToMany(mappedBy = "video")
	private Collection<VideoProfileEntity> videos;
}
