package fr.formation.webflix.entities;

import javax.persistence.*;

@Entity
@Table(name = "video_profile")
public class VideoProfileEntity {

	@EmbeddedId
	private VideoProfileEmbeddedID id;

	@ManyToOne
	@MapsId("profileId")
	private ProfileEntity profile;

	@ManyToOne
	@MapsId("videoId")
	private VideoEntity video;

	@Column(nullable = false, columnDefinition = "smallint unsigned")
	private Integer timestamp;
}
