package guru.springframework.recipe.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import guru.springframework.recipe.services.RecipeService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class IndexController {

	private final RecipeService recipeService;
	
	public IndexController(RecipeService recipeService) {
		log.debug("Getting index page here");
		this.recipeService = recipeService;
	}


	@RequestMapping({"", "/", "/index"})
	public String getIndexPage(Model model) {
		
		model.addAttribute("recipes", recipeService.getRecipes());
		
		return "index";
	}
	
}
