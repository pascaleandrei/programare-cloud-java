package com.example.demo.controller;

import com.example.demo.model.Recipe;
import com.example.demo.service.RecipeBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recipe")
public class RecipeBookController {
    private final RecipeBookService recipeBookService;

    @Autowired
    public RecipeBookController(RecipeBookService recipeBookService) {
        this.recipeBookService = recipeBookService;
    }

    @GetMapping
    public ResponseEntity<List<Recipe>> getAllRecipes() {
        return ResponseEntity.ok(recipeBookService.getAllRecipes());
    }

    @PostMapping
    public ResponseEntity<?> saveRecipe(@RequestBody Recipe recipe) {
        try {
            return ResponseEntity.ok(recipeBookService.saveRecipe(recipe));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
