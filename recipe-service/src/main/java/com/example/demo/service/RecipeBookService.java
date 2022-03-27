package com.example.demo.service;

import com.example.demo.model.Recipe;

import java.util.List;

public interface RecipeBookService {
    List<Recipe> getAllRecipes();
    Recipe saveRecipe(Recipe recipe);
}
