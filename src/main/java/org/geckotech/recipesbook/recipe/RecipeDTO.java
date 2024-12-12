package org.geckotech.recipesbook.recipe;

import org.geckotech.recipesbook.ingredient.IngredientDTO;

import java.util.List;

public record RecipeDTO(
        Long id,
        String name,
        String description,
        int timeRequired,
        String mealType,
        List< IngredientDTO > ingredients
) {}