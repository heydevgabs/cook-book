package org.geckotech.recipesbook.recipe;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.geckotech.recipesbook.common.MealType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recipes")
@Tag(name = "Recipe", description = "Recipe management APIs for creating, retrieving, searching, and deleting recipes.")
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @Operation(
            summary = "Create a new recipe",
            description = "Endpoint to create a new recipe. Provide recipe details such as name, description, time required, meal type, and ingredients.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Recipe information to create",
                    content = @Content(schema = @Schema(implementation = RecipeDTO.class))
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Recipe created successfully",
                            content = @Content(schema = @Schema(implementation = RecipeDTO.class))
                    )
            }
    )
    @PostMapping
    public RecipeDTO createRecipe(@RequestBody RecipeDTO recipeDTO) {
        return recipeService.createRecipe(recipeDTO);
    }

    @Operation(
            summary = "Get all recipes",
            description = "Retrieves a list of all recipes stored in the system.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "List of recipes retrieved successfully",
                            content = @Content(schema = @Schema(implementation = RecipeDTO[].class))
                    )
            }
    )
    @GetMapping
    public List<RecipeDTO> getAllRecipes() {
        return recipeService.getAllRecipes();
    }

    @Operation(
            summary = "Get recipe by ID",
            description = "Fetches a specific recipe using its unique ID.",
            parameters = @Parameter(
                    name = "id",
                    description = "ID of the recipe to retrieve",
                    required = true
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Recipe found",
                            content = @Content(schema = @Schema(implementation = RecipeDTO.class))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Recipe not found"
                    )
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<RecipeDTO> getRecipeById(
            @PathVariable Long id
    ) {
        return recipeService.getRecipeById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Search recipes by name",
            description = "Searches recipes by a partial or complete name.",
            parameters = @Parameter(
                    name = "name",
                    description = "Recipe name or part of the name to search for",
                    required = true
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Recipes found",
                            content = @Content(schema = @Schema(implementation = RecipeDTO[].class))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "No recipes found"
                    )
            }
    )
    @GetMapping("/search")
    public List<RecipeDTO> searchRecipes(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) List< MealType > mealTypes
    ) {
        if (name != null && mealTypes != null) {
            return recipeService.searchRecipes(name, mealTypes);
        } else if (name != null) {
            return recipeService.searchByName(name);
        } else if (mealTypes != null) {
            return recipeService.searchByMealTypes(mealTypes);
        } else {
            return List.of(); // Return an empty list if no filters are provided.
        }
    }

    @Operation(
            summary = "Delete a recipe",
            description = "Deletes a recipe by its unique ID. This action is irreversible.",
            parameters = @Parameter(
                    name = "id",
                    description = "ID of the recipe to delete",
                    required = true
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "Recipe deleted successfully"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Recipe not found"
                    )
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecipe(
            @PathVariable Long id
    ) {
        recipeService.deleteRecipe(id);
        return ResponseEntity.noContent().build();
    }
}