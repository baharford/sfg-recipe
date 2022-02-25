package guru.springframework.recipe.controllers;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import guru.springframework.recipe.commands.RecipeCommand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import guru.springframework.recipe.model.Recipe;
import guru.springframework.recipe.services.RecipeService;

class RecipeControllerTest {

	@Mock 
	RecipeService recipeService;
	
	RecipeController controller;
	MockMvc mockMvc;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
		
		controller = new RecipeController(recipeService);
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	@Test
	void testGetRecipe() throws Exception {
		Recipe recipe = new Recipe();
		recipe.setId(1L);
		
		when(recipeService.findById(anyLong())).thenReturn(recipe);

		mockMvc.perform(get("/recipe/1/show"))
			.andExpect(status().isOk())
			.andExpect(view().name("recipe/show"));
	}

	@Test
	void testGetNewRecipeForm() throws Exception {
		RecipeCommand command = new RecipeCommand();

		mockMvc.perform(get("/recipe/new"))
				.andExpect(status().isOk())
				.andExpect(view().name("recipe/recipeform"))
				.andExpect(model().attributeExists("recipe"));
	}

	@Test
	void testGetUpdateView() throws Exception {
		RecipeCommand command = new RecipeCommand();
		command.setId(2L);

		when(recipeService.findCommandById(anyLong())).thenReturn(command);

		mockMvc.perform(get("/recipe/2/update"))
				.andExpect(status().isOk())
				.andExpect(view().name("recipe/recipeform"))
				.andExpect(model().attributeExists("recipe"));

	}


	@Test
	public void testDeleteAction() throws Exception {
		mockMvc.perform(get("/recipe/1/delete"))
				.andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/"));

		verify(recipeService, times(1)).deleteById(anyLong());
	}

}
