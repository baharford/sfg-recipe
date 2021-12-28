package guru.springframework.recipe.services;

import java.util.Set;

import guru.springframework.recipe.model.Recipe;

public interface RecipeService {
	Set<Recipe> getRecipes();
}
