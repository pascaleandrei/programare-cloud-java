package com.unitbv.controller;

import com.unitbv.request.CreateRecipeRequest;
import com.unitbv.service.PantryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/recipe")
public class RecipeController {
    private final RestTemplate restTemplate = new RestTemplate();
    private final static String recipeUrl = "http://localhost:8081/recipe";
    private final PantryService pantryService;

    @Autowired
    public RecipeController(PantryService pantryService) {
        this.pantryService = pantryService;
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllRecipes() {
        return restTemplate.getForEntity(recipeUrl, String.class);
    }

    @PostMapping("/new")
    public ResponseEntity<?> saveRecipe(@RequestBody CreateRecipeRequest request) {
        try {
            HttpEntity<?> httpEntity = new HttpEntity<>(request);
            ResponseEntity<?> response = restTemplate
                    .exchange(recipeUrl, HttpMethod.POST, httpEntity, Object.class);
            pantryService.saveAllIngredients(request.getIngredients());
            return ResponseEntity.ok(response.getBody());
        } catch (HttpClientErrorException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }
}
