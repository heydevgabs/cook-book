package org.geckotech.recipesbook.recipe;


import org.geckotech.recipesbook.common.MealType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recipes")
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping
    public ResponseEntity< List<RecipeDTO> > getAllRecipes() {
        return ResponseEntity.ok(recipeService.getAllRecipes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecipeDTO> getRecipeById(@PathVariable Long id) {
        return recipeService.getRecipeById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<RecipeDTO> createRecipe(@RequestBody RecipeDTO recipeDTO) {
        RecipeDTO createdRecipe = recipeService.createRecipe(recipeDTO);
        return ResponseEntity.ok(createdRecipe);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecipe(@PathVariable Long id) {
        recipeService.deleteRecipe(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<RecipeDTO>> searchRecipes(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) List<MealType> mealTypes) {
        if (name == null && mealTypes == null) {
            return ResponseEntity.badRequest().build();
        }

        List<RecipeDTO> results = recipeService.searchRecipes(name, mealTypes);
        return ResponseEntity.ok(results);
    }

}