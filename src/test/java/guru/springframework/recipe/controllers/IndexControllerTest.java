package guru.springframework.recipe.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import guru.springframework.recipe.model.Recipe;
import guru.springframework.recipe.services.RecipeService;

class IndexControllerTest {
	@Mock
	RecipeService recipeService;
	
	@Mock
	Model model;

	IndexController indexController;
	
	@BeforeEach
	public void setup() throws Exception {
		MockitoAnnotations.openMocks(this);
		
		indexController = new IndexController(recipeService);
	}	

	@Test
	void testGetIndexPage() throws Exception {
		
		// given
		Set<Recipe> recipes = new HashSet<>();

		recipes.add(new Recipe());
	
		// have to make 2nd recipe unique to first
		Recipe recipe = new Recipe();
		recipe.setId(1L);
		recipes.add(recipe);
		
		// when
		when(recipeService.getRecipes()).thenReturn(recipes);
		
		ArgumentCaptor<Set<Recipe>> argumentCaptor = ArgumentCaptor.forClass(Set.class);
		
		// then
		String viewName = indexController.getIndexPage(model);
		assertEquals("index", viewName);
		verify(recipeService, times(1)).getRecipes();
		verify(model, times(1)).addAttribute(eq("recipes"), argumentCaptor.capture());
		Set<Recipe> setInController = argumentCaptor.getValue();
		assertEquals(2, setInController.size());
	}

}
