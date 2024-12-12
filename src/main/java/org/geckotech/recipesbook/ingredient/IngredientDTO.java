package org.geckotech.recipesbook.ingredient;

public record IngredientDTO(
        Long id, String name,
        String amount
) {}