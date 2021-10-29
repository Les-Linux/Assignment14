package com.elbicon.codercampus.assignment9;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class RecipeController {
    @Autowired
    private RecipeService recipeService;

    @GetMapping("/all-recipes")
    public String allRecipes() {
        List<Recipe> allRecipes = recipeService.getRecipes()
                .stream()
                .collect(Collectors.toList());

        return RecipeList(allRecipes);
    }

    @GetMapping("/gluten-free")
    public String glutenFreeRecipes() {
        List<Recipe> glutenFreeRecipes = recipeService.getRecipes()
                .stream()
                .filter(ingredient -> ingredient.getGlutenFree() == Boolean.TRUE)
                .collect(Collectors.toList());

        return RecipeList(glutenFreeRecipes);
    }

    @GetMapping("/vegan")
    public String veganRecipes() {
        //return "Hello World!";
        List<Recipe> veganRecipes = recipeService.getRecipes()
                .stream()
                .filter(ingredient -> ingredient.getVegan() == Boolean.TRUE)
                .collect(Collectors.toList());

        return RecipeList(veganRecipes);
    }

    @GetMapping("/vegan-and-gluten-free")
    public String veganGlutenFreeRecipes(){
        List<Recipe> veganGlutenFreeRecipes = recipeService.getRecipes()
                .stream()
                .filter(ingredient -> ingredient.getGlutenFree() == Boolean.TRUE && ingredient.getVegan() == Boolean.TRUE)
                .collect(Collectors.toList());

        return RecipeList(veganGlutenFreeRecipes);
    }

    @GetMapping("/vegetarian")
    public String vegetarianRecipes(){
        List<Recipe> vegetarian = recipeService.getRecipes()
                .stream()
                .filter(ingredient -> ingredient.getVegetarian() == Boolean.TRUE)
                .collect(Collectors.toList());

        return RecipeList(vegetarian);
    }
    private String RecipeList(List<Recipe> recipes) {
        StringBuilder recipeList = new StringBuilder();
        for (Recipe recipe : recipes) {
            recipeList.append("<b>Title:</b>" + "<span style=\"color:blue\">" + recipe.getTitle() + "</span></br>")
                    .append("<b>Cooking Minutes:</b> " + recipe.getCookingMinutes() + "<br>")
                    .append("<b>Dairy Free:</b> " + Boolean.valueOf(recipe.getDairyFree()) + "<br>")
                    .append("<b>Gluten Free:</b> " + Boolean.valueOf(recipe.getGlutenFree()) + "<br>")
                    .append("<b>Instructions:</b> " + recipe.getInstructions() + "<br>")
                    .append("<b>Preparation Minutes:</b> " + recipe.getPreparationMinutes().toString() + "<br>")
                    .append("<b>Price Per Serving:</b> " + recipe.getPricePerServing().toString() + "<br>")
                    .append("<b>Ready In Minutes:</b> " + recipe.getReadyInMinutes().toString() + "<br>")
                    .append("<b>Servings:</b> " + recipe.getServings().toString() + "<br>")
                    .append("<b>Spoonacular Score:</b> " + recipe.getSpoonacularScore().toString() + "<br>")
                    .append("<b>Vegan:</b> " + Boolean.valueOf(recipe.getVegan()) + "<br>")
                    .append("<b>Vegatarian:</b> " + Boolean.valueOf(recipe.getVegetarian()) + "<br>")
                    .append("<br>");
        }
        return recipeList.toString();
    }
}
