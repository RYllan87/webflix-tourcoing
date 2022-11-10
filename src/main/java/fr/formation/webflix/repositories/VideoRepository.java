package fr.formation.webflix.repositories;

import fr.formation.webflix.entities.VideoEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoRepository extends CrudRepository<VideoEntity, Long> {
}
