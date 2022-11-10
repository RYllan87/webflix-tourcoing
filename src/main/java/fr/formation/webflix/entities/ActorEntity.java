package fr.formation.webflix.entities;

import fr.formation.webflix.enums.Gender;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Collection;

@Entity
@Table(name = "actor")
public class ActorEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 100, nullable = false)
	private String firstname;

	@Column(length = 100)
	private String  lastname;

	@Temporal(TemporalType.DATE)
	private Calendar birthday;

	@Column(nullable = false, columnDefinition = "VARCHAR(10) default 'MR'")
	@Enumerated(EnumType.STRING)
	private Gender gender;

	private String photo;

	@Column(columnDefinition = "TINYINT DEFAULT FALSE")
	private Boolean producer;

	@ManyToMany(mappedBy = "actors")
	private Collection<VideoEntity> videos;
}
