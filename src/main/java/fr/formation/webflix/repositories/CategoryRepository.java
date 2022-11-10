package fr.formation.webflix.repositories;

import fr.formation.webflix.entities.CategoryEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Repository
public interface CategoryRepository extends CrudRepository<CategoryEntity, Long> {

//    @Query("FROM CategoryEntity where name = :name")
//    Optional<CategoryEntity> s(String name);

    Optional<CategoryEntity> findByName(String name);
}
