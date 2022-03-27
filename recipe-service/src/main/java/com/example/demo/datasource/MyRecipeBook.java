package com.example.demo.datasource;

import com.example.demo.model.Recipe;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MyRecipeBook {
    private final List<Recipe> recipes = new ArrayList<>();

    public List<Recipe> getAllRecipes() {
        return recipes;
    }

    public Recipe saveRecipe(Recipe recipe) {
        if (recipe.getName() == null || recipe.getName().isEmpty()) {
            throw new RuntimeException("Can't add a recipe with no name");
        }
        recipe.getIngredients().forEach(ingredient -> {
            if (ingredient.getName() == null || ingredient.getName().isEmpty()) {
                throw new RuntimeException("Can't use an ingredient with no name");
            }
        });
        this.recipes.add(recipe);

        return recipe;
    }
}
