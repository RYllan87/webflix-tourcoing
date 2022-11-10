package fr.formation.webflix.entities;


import ch.qos.logback.core.joran.spi.NoAutoStart;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Calendar;
import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "video")
public class VideoEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Pattern(regexp = ".*{2,255}", message = "Ce champ doit contenir entre 2 et 255 caractères!")
	@Column(nullable = false)
	private String name;

	@Column(nullable = false, columnDefinition = "smallint unsigned")
	private Integer duration;

	@Pattern(regexp = "[a-z \\-_.]{2,60}",
			flags = {Pattern.Flag.DOTALL, Pattern.Flag.CASE_INSENSITIVE},
			message = "Ce champ comporte entre 2 et 60 caractères."
	)
	@Column(nullable = false, length = 60)
	private String originCountry;

	@Column(columnDefinition = "TEXT")
	private String synopsis;
	@Column(nullable = false)
	private String urlVideo;
	@Column(nullable = false)
	private String cover;

	@DecimalMin(value = "1900")
	@DecimalMax(value = "2100")
	@Column(columnDefinition = "smallint unsigned")
	private Integer productYear;

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private Calendar datePublished;
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private Calendar dateDeleted;

	@OneToMany(mappedBy = "video")
	private Collection<VideoProfileEntity> profiles;

@JsonBackReference
@ToString.Exclude
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	private CategoryEntity category;
@JsonBackReference
@ManyToMany
	@JoinTable(
			name = "video_actor",
			joinColumns = @JoinColumn(name = "video_id"),
			inverseJoinColumns = @JoinColumn(name = "actor_id")
	)
	private Collection<ActorEntity> actors;
}
