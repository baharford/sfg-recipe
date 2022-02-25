package guru.springframework.recipe.services;

import java.util.Set;

import guru.springframework.recipe.commands.RecipeCommand;
import guru.springframework.recipe.model.Recipe;

public interface RecipeService {
	Set<Recipe> getRecipes();

	Recipe findById(Long id);
	RecipeCommand findCommandById(Long id);

	RecipeCommand saveRecipeCommand(RecipeCommand command);

	void deleteById(Long idToDelete);
}
