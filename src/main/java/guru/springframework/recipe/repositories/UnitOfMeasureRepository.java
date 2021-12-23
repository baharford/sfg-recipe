package guru.springframework.recipe.repositories;

import org.springframework.data.repository.CrudRepository;

import guru.springframework.recipe.model.UnitOfMeasure;

public interface UnitOfMeasureRepository extends CrudRepository<UnitOfMeasure, Long> {

}
