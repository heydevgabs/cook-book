package org.geckotech.recipesbook.recipe;

import org.geckotech.recipesbook.common.MealType;
import org.geckotech.recipesbook.ingredient.Ingredient;
import org.geckotech.recipesbook.ingredient.IngredientDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecipeService {

    private final RecipeRepository recipeRepository;

    public RecipeService(RecipeRepository recipeRepository ) {
        this.recipeRepository = recipeRepository;
    }

    public List<RecipeDTO> getAllRecipes() {
        return recipeRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    public Optional<RecipeDTO> getRecipeById( Long id) {
        return recipeRepository.findById(id)
                .map(this::mapToDTO);
    }

    public RecipeDTO createRecipe(RecipeDTO recipeDTO) {
        Recipe recipe = mapToEntity(recipeDTO);
        Recipe savedRecipe = recipeRepository.save(recipe);
        return mapToDTO(savedRecipe);
    }

    public void deleteRecipe(Long id) {
        recipeRepository.deleteById(id);
    }

    public List<RecipeDTO> searchByName(String name) {
        return recipeRepository.findByNameContainingIgnoreCase(name)
                .stream()
                .map(this::mapToDTO)
                .toList();
    }


    public List<RecipeDTO> searchByMealTypes(List<MealType> mealTypes) {
        return recipeRepository.findByMealTypeIn(mealTypes)
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    public List<RecipeDTO> searchRecipes(String name, List<MealType> mealTypes) {
        if (name != null && mealTypes != null) {
            return recipeRepository.findByNameContainingIgnoreCaseAndMealTypeIn(name, mealTypes)
                    .stream()
                    .map(this::mapToDTO)
                    .toList();
        } else if (name != null) {
            return searchByName(name);
        } else if (mealTypes != null) {
            return searchByMealTypes(mealTypes);
        } else {
            return List.of();
        }
    }

    private RecipeDTO mapToDTO(Recipe recipe) {
        if (recipe == null) return null;

        return new RecipeDTO(
                recipe.getId(),
                recipe.getName(),
                recipe.getDescription(),
                recipe.getTimeRequired(),
                recipe.getMealType() != null ? recipe.getMealType().name() : null,
                recipe.getIngredients() != null ? recipe.getIngredients().stream()
                        .map(ingredient -> new IngredientDTO(
                                ingredient.getId(),
                                ingredient.getName(),
                                ingredient.getAmount()
                        ))
                        .toList() : null
        );
    }

    private Recipe mapToEntity(RecipeDTO recipeDTO) {
        if (recipeDTO == null) return null;

        Recipe recipe = new Recipe();
        recipe.setId(recipeDTO.id());
        recipe.setName(recipeDTO.name());
        recipe.setDescription(recipeDTO.description());
        recipe.setTimeRequired(recipeDTO.timeRequired());
        recipe.setMealType(recipeDTO.mealType() != null ? MealType.valueOf(recipeDTO.mealType()) : null);

        if (recipeDTO.ingredients() != null) {
            recipe.setIngredients(recipeDTO.ingredients().stream()
                    .map(ingredientDTO -> {
                        Ingredient ingredient = new Ingredient();
                        ingredient.setId(ingredientDTO.id());
                        ingredient.setName(ingredientDTO.name());
                        ingredient.setAmount(ingredientDTO.amount());
                        return ingredient;
                    })
                    .toList());
        }

        return recipe;
    }

}
