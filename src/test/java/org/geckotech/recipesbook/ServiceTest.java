package org.geckotech.recipesbook;

import org.geckotech.recipesbook.common.MealType;
import org.geckotech.recipesbook.ingredient.Ingredient;
import org.geckotech.recipesbook.ingredient.IngredientDTO;
import org.geckotech.recipesbook.recipe.Recipe;
import org.geckotech.recipesbook.recipe.RecipeDTO;
import org.geckotech.recipesbook.recipe.RecipeRepository;
import org.geckotech.recipesbook.recipe.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith( MockitoExtension.class)
public class ServiceTest {
    @Mock
    private RecipeRepository recipeRepository;

    @InjectMocks
    private RecipeService recipeService;

    private Recipe testRecipe;
    private RecipeDTO testRecipeDTO;

    @BeforeEach
    void setUp() {
        testRecipe = new Recipe();
        testRecipe.setId(1L);
        testRecipe.setName("Test Recipe");
        testRecipe.setDescription("Test Description");
        testRecipe.setTimeRequired(30);
        testRecipe.setMealType( MealType.DINNER);

        Ingredient ingredient = new Ingredient();
        ingredient.setId(1L);
        ingredient.setName("Test Ingredient");
        ingredient.setAmount("100g");
        testRecipe.setIngredients( List.of(ingredient));

        testRecipeDTO = new RecipeDTO(
                1L,
                "Test Recipe",
                "Test Description",
                30,
                "DINNER",
                List.of(new IngredientDTO(1L, "Test Ingredient", "100g"))
        );
    }

    @Test
    void getAllRecipes_ShouldReturnListOfRecipeDTOs() {
        when(recipeRepository.findAll()).thenReturn(List.of(testRecipe));

        List<RecipeDTO> result = recipeService.getAllRecipes();

        assertThat(result).isNotEmpty();
        assertThat(result.get(0).name()).isEqualTo(testRecipe.getName());
        verify(recipeRepository).findAll();
    }

    @Test
    void getRecipeById_WhenRecipeExists_ShouldReturnRecipeDTO() {
        when(recipeRepository.findById(1L)).thenReturn( Optional.of(testRecipe));

        Optional<RecipeDTO> result = recipeService.getRecipeById(1L);

        assertThat(result).isPresent();
        assertThat(result.get().name()).isEqualTo(testRecipe.getName());
        verify(recipeRepository).findById(1L);
    }

    @Test
    void getRecipeById_WhenRecipeDoesNotExist_ShouldReturnEmpty() {
        when(recipeRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<RecipeDTO> result = recipeService.getRecipeById(1L);

        assertThat(result).isEmpty();
        verify(recipeRepository).findById(1L);
    }

    @Test
    void createRecipe_ShouldReturnSavedRecipeDTO() {
        when(recipeRepository.save(any(Recipe.class))).thenReturn(testRecipe);

        RecipeDTO result = recipeService.createRecipe(testRecipeDTO);

        assertThat(result).isNotNull();
        assertThat(result.name()).isEqualTo(testRecipeDTO.name());
        verify(recipeRepository).save(any(Recipe.class));
    }

    @Test
    void deleteRecipe_ShouldCallRepository() {
        recipeService.deleteRecipe(1L);

        verify(recipeRepository).deleteById(1L);
    }

    @Test
    void searchByName_ShouldReturnMatchingRecipes() {
        when(recipeRepository.findByNameContainingIgnoreCase("Test"))
                .thenReturn(List.of(testRecipe));

        List<RecipeDTO> result = recipeService.searchByName("Test");

        assertThat(result).isNotEmpty();
        assertThat(result.get(0).name()).isEqualTo(testRecipe.getName());
        verify(recipeRepository).findByNameContainingIgnoreCase("Test");
    }

    @Test
    void searchRecipes_ShouldReturnMatchingRecipes() {
        String name = "Test Recipe";
        List<MealType> mealTypes = List.of(MealType.DINNER);

        when(recipeRepository.findByNameContainingIgnoreCaseAndMealTypeIn(name, mealTypes))
                .thenReturn(List.of(testRecipe));
        List<RecipeDTO> result = recipeService.searchRecipes(name, mealTypes);

        assertThat(result).isNotEmpty();
        assertThat(result.get(0).name()).isEqualTo(testRecipe.getName());
        assertThat(result.get(0).mealType()).isEqualTo(MealType.DINNER.name());
        verify(recipeRepository).findByNameContainingIgnoreCaseAndMealTypeIn(name, mealTypes);
    }


    @Test
    void searchByMealTypes_ShouldReturnMatchingRecipes() {
        List<MealType> mealTypes = List.of(MealType.DINNER);
        when(recipeRepository.findByMealTypeIn(mealTypes))
                .thenReturn(List.of(testRecipe));

        List<RecipeDTO> result = recipeService.searchByMealTypes(mealTypes);

        assertThat(result).isNotEmpty();
        assertThat(result.get(0).name()).isEqualTo(testRecipe.getName());
        verify(recipeRepository).findByMealTypeIn(mealTypes);
    }
}