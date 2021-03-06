package guru.springframework.recipe.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import guru.springframework.recipe.converters.RecipeCommandToRecipe;
import guru.springframework.recipe.converters.RecipeToRecipeCommand;
import guru.springframework.recipe.model.Recipe;
import guru.springframework.recipe.repositories.RecipeRepository;

class RecipeServiceImplTest {

    RecipeServiceImpl recipeService;

    @Mock
    RecipeRepository recipeRepository;

    @Mock
    RecipeToRecipeCommand recipeToRecipeCommand;

    @Mock
    RecipeCommandToRecipe recipeCommandToRecipe;
	
	@BeforeEach
	public void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
		
		recipeService = new RecipeServiceImpl(recipeRepository, recipeCommandToRecipe, recipeToRecipeCommand);
	}

	@Test
	void getRecipeByIdTest() throws Exception {
		
		Recipe recipe = new Recipe();
		recipe.setId(1L);
		Optional<Recipe> recipeOptional = Optional.of(recipe);
		
		when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);
		
		
		HashSet recipeData = new HashSet();
		recipeData.add(recipe);
		
		Mockito.when(recipeRepository.findAll()).thenReturn(recipeData);
		
		Recipe recipeReturned = recipeService.findById(1L);
		
		assertNotNull(recipeReturned, "Null recipe returned");
		verify(recipeRepository, times(1)).findById(anyLong());
		verify(recipeRepository, never()).findAll();
	}
	
	@Test
	void getRecipesTest() throws Exception {
		
		Recipe recipe = new Recipe();
		HashSet recipeData = new HashSet();
		recipeData.add(recipe);
		
		Mockito.when(recipeRepository.findAll()).thenReturn(recipeData);
		
		Set<Recipe> recipes = recipeService.getRecipes();
		
		assertEquals(recipes.size(), 1);
		Mockito.verify(recipeRepository, times(1)).findAll();
	}

	@Test
	void testDeleteById() throws Exception {

		//given
		Long idToDelete = 2L;

		//when
		recipeService.deleteById(idToDelete);

		//no 'when', since method has void return type

		//then
		verify(recipeRepository, times(1)).deleteById(anyLong());
	}

}
