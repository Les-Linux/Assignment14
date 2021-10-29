package com.elbicon.codercampus.assignment9;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;

@Service
public class RecipeService {
    private ArrayList<Recipe> recipes = new ArrayList<>();

    @Bean
    public ArrayList<Recipe> getRecipes(){
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(MessageFormat.format("src/main/resources/{0}","recipes.txt")))) {
            CSVFormat.Builder csvBuilder = CSVFormat.Builder.create();

            Iterable<CSVRecord> csvRecords = csvBuilder.setIgnoreSurroundingSpaces(true)
                    .setSkipHeaderRecord(false)
                    .setHeader()
                    .setQuote('"')
                    .setEscape('\\')
                    .build()
                    .parse(bufferedReader);

            for (CSVRecord record : csvRecords){
                Recipe recipe = new Recipe();
                recipe.setCookingMinutes(Integer.valueOf(record.get("Cooking Minutes")));
                recipe.setDairyFree(Boolean.valueOf(record.get("Dairy Free")));
                recipe.setGlutenFree(Boolean.valueOf(record.get("Gluten Free")));
                recipe.setInstructions(record.get("Instructions"));
                recipe.setPreparationMinutes(Double.valueOf(record.get("Preparation Minutes")));
                recipe.setPricePerServing(Double.valueOf(record.get("Price Per Serving")));
                recipe.setReadyInMinutes(Integer.valueOf(record.get("Ready In Minutes")));
                recipe.setServings(Integer.valueOf(record.get("Servings")));
                recipe.setSpoonacularScore(Double.valueOf(record.get("Spoonacular Score")));
                recipe.setTitle(record.get("Title"));
                recipe.setVegan(Boolean.valueOf(record.get("Vegan")));
                recipe.setVegetarian(Boolean.valueOf(record.get("Vegetarian")));
                recipes.add(recipe);
            }
        }catch(IOException e){
            System.out.println("Exception Caught - " +  e.getMessage());;
        }
        return recipes;
    }
}
