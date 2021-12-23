package guru.springframework.recipe.repositories;

import org.springframework.data.repository.CrudRepository;

import guru.springframework.recipe.model.Category;

public interface CategoryRepository extends CrudRepository<Category, Long> {

}
