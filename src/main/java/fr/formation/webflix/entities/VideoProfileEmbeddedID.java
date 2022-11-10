package fr.formation.webflix.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class VideoProfileEmbeddedID implements Serializable {
	 private static final Long serialVersionUID = 1L;

	 private Long videoId;
	 private Long profileId;
}
