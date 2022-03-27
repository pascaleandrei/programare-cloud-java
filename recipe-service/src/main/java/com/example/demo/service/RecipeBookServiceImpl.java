package com.example.demo.service;

import com.example.demo.datasource.MyRecipeBook;
import com.example.demo.model.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipeBookServiceImpl implements RecipeBookService{
    private final MyRecipeBook  recipeBook;

    @Autowired
    public RecipeBookServiceImpl(MyRecipeBook recipeBook) {
        this.recipeBook = recipeBook;
    }

    @Override
    public List<Recipe> getAllRecipes() {
        return recipeBook.getAllRecipes();
    }

    @Override
    public Recipe saveRecipe(Recipe recipe) {
        return recipeBook.saveRecipe(recipe);
    }
}
