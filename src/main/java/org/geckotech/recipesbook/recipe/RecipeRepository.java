package org.geckotech.recipesbook.recipe;

import org.geckotech.recipesbook.common.MealType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    List<Recipe> findByNameContainingIgnoreCase( String name);
    List<Recipe> findByMealTypeIn(List< MealType > mealTypes);
    List<Recipe> findByNameContainingIgnoreCaseAndMealTypeIn(String name, List<MealType> mealTypes);

}
