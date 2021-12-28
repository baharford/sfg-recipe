package guru.springframework.recipe.bootstrap;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import guru.springframework.recipe.model.Category;
import guru.springframework.recipe.model.Difficulty;
import guru.springframework.recipe.model.Ingredient;
import guru.springframework.recipe.model.Notes;
import guru.springframework.recipe.model.Recipe;
import guru.springframework.recipe.model.UnitOfMeasure;
import guru.springframework.recipe.repositories.CategoryRepository;
import guru.springframework.recipe.repositories.RecipeRepository;
import guru.springframework.recipe.repositories.UnitOfMeasureRepository;

@Component
public class RecipeBootstrap implements ApplicationListener<ContextRefreshedEvent> {

	private final CategoryRepository categoryRepository;
	private final RecipeRepository recipeRepository;
	private final UnitOfMeasureRepository unitOfMeasureRepository;
	
	public RecipeBootstrap(CategoryRepository categoryRepository, RecipeRepository recipeRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
		this.categoryRepository = categoryRepository;
		this.recipeRepository = recipeRepository;
		this.unitOfMeasureRepository = unitOfMeasureRepository;
	}
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		recipeRepository.saveAll(getRecipes());
	}
	
	private List<Recipe> getRecipes() { 
		
		List<Recipe> recipes = new ArrayList<>();
		
		// get UOMs
		Optional<UnitOfMeasure> eachUomOptional = unitOfMeasureRepository.findByDescription("Each");
		
		if(!eachUomOptional.isPresent()) { 
			throw new RuntimeException("Expected UOM Not Found");
		}
		
		Optional<UnitOfMeasure> tablespoonUomOptional = unitOfMeasureRepository.findByDescription("Tablespoon");
		
		if(!eachUomOptional.isPresent()) { 
			throw new RuntimeException("Expected UOM Not Found");
		}
		
		Optional<UnitOfMeasure> teaspoonUomOptional = unitOfMeasureRepository.findByDescription("Teaspoon");
		
		if(!eachUomOptional.isPresent()) { 
			throw new RuntimeException("Expected UOM Not Found");
		}
		
		Optional<UnitOfMeasure> dashUomOptional = unitOfMeasureRepository.findByDescription("Dash");
		
		if(!eachUomOptional.isPresent()) { 
			throw new RuntimeException("Expected UOM Not Found");
		}
		
		Optional<UnitOfMeasure> pintUomOptional = unitOfMeasureRepository.findByDescription("Pint");
		
		if(!eachUomOptional.isPresent()) { 
			throw new RuntimeException("Expected UOM Not Found");
		}
		
		Optional<UnitOfMeasure> cupUomOptional = unitOfMeasureRepository.findByDescription("Cup");
		
		if(!eachUomOptional.isPresent()) { 
			throw new RuntimeException("Expected UOM Not Found");
		}
		
		// get optionals
		UnitOfMeasure eachUom = eachUomOptional.get();
		UnitOfMeasure tablespoonUom = tablespoonUomOptional.get();
		UnitOfMeasure teaspoonUom = teaspoonUomOptional.get();
		UnitOfMeasure dashUom = dashUomOptional.get();
		UnitOfMeasure pintUom = pintUomOptional.get();
		UnitOfMeasure cupUom = cupUomOptional.get();
		
		// get categories
		Optional<Category> americanCategoryOptional = categoryRepository.findByDescription("American");
		
		if(!americanCategoryOptional.isPresent()) {
			throw new RuntimeException("Expected Category Not Found");
		}
		
		Optional<Category> mexicanCategoryOptional = categoryRepository.findByDescription("Mexican");
		
		if(!americanCategoryOptional.isPresent()) {
			throw new RuntimeException("Expected Category Not Found");
		}
		
		Category americanCategory = americanCategoryOptional.get();
		Category mexicanCategory = mexicanCategoryOptional.get();
		
		// Yummy Guac
		Recipe guacRecipe = new Recipe();
		guacRecipe.setDescription("Perfect Guacamole");
		guacRecipe.setPrepTime(10);
		guacRecipe.setCookTime(0);
		guacRecipe.setDifficulty(Difficulty.EASY);
		guacRecipe.setDirections("1 Cut avado, remove flesh: Cut the avacados in half.  Remove seed.  Score the inside of the avacodo with blunt knife and scoop out the inside." +
								"\n" + 
								"2 Mash with a ford: Using a fork, roughtly mash the avocado" + 
								"\n" + 
								"3 Add salt, lime juice, and the rest");
		
	
		Notes guacNotes = new Notes();
		guacNotes.setRecipeNotes("For a very quick guacamole just take a 1/4 cup of salsa and mix it in with your mashed avocados.\n" + 
				"Feel free to experiment!  blah blah, this is the notes section"
				);
		guacNotes.setRecipe(guacRecipe);
		guacRecipe.setNotes(guacNotes);
		
		guacRecipe.getIngredients().add(new Ingredient("ripe avocados", new BigDecimal(2), eachUom, guacRecipe));
		guacRecipe.getIngredients().add(new Ingredient("kosher salt", new BigDecimal(.5), teaspoonUom, guacRecipe));
		guacRecipe.getIngredients().add(new Ingredient("fresh lime juice or lemon juice", new BigDecimal(2), tablespoonUom, guacRecipe));
		guacRecipe.getIngredients().add(new Ingredient("minced red onion", new BigDecimal(2), tablespoonUom, guacRecipe));
		guacRecipe.getIngredients().add(new Ingredient("serrano chiles", new BigDecimal(2), eachUom, guacRecipe));
		guacRecipe.getIngredients().add(new Ingredient("cilantro", new BigDecimal(2), dashUom, guacRecipe));
		
		guacRecipe.getCategories().add(americanCategory);
		guacRecipe.getCategories().add(mexicanCategory);
		
		recipes.add(guacRecipe);
		
		
		// Yummy Tacos
		Recipe tacoRecipe = new Recipe();
		tacoRecipe.setDescription("Spicy Grilled Chicken Taco");
		tacoRecipe.setPrepTime(9);
		tacoRecipe.setCookTime(20);
		tacoRecipe.setDifficulty(Difficulty.MODERATE);
		tacoRecipe.setDirections("1 Prepare gas or charcoal grill for midium-high, direct heat" +
								"\n" + 
								"2 Make the marinade and coat the chicken" + 
								"\n" + 
								"3 Grill the chicken, yada, yada, yada");

		Notes tacoNotes = new Notes();
		tacoNotes.setRecipeNotes("These are notes about the tacos...");
		tacoNotes.setRecipe(tacoRecipe);
		tacoRecipe.setNotes(tacoNotes);
		
		
		tacoRecipe.getIngredients().add(new Ingredient("Ancho Chili Powder", new BigDecimal(2), pintUom, tacoRecipe));
		tacoRecipe.getIngredients().add(new Ingredient("Dried Oregano", new BigDecimal(1), cupUom, tacoRecipe));
		tacoRecipe.getIngredients().add(new Ingredient("Dried Cumin", new BigDecimal(1), teaspoonUom, tacoRecipe));
		tacoRecipe.getIngredients().add(new Ingredient("Boneless Chicken Thighs", new BigDecimal(4), eachUom, tacoRecipe));
		tacoRecipe.getIngredients().add(new Ingredient("Small Corn Tortillas", new BigDecimal(8), eachUom, tacoRecipe));

		tacoRecipe.getCategories().add(mexicanCategory);
		tacoRecipe.getCategories().add(americanCategory);
		
		recipes.add(tacoRecipe);
		
		return recipes;
		
	}


	
	
}
