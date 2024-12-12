package org.geckotech.recipesbook.recipe;

import jakarta.persistence.*;
import org.geckotech.recipesbook.common.MealType;
import org.geckotech.recipesbook.ingredient.Ingredient;

import java.util.List;

@Entity
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public String getName( ) {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }

    public String getDescription( ) {
        return description;
    }

    public void setDescription( String description ) {
        this.description = description;
    }

    public int getTimeRequired( ) {
        return timeRequired;
    }

    public void setTimeRequired( int timeRequired ) {
        this.timeRequired = timeRequired;
    }

    public MealType getMealType( ) {
        return mealType;
    }

    public void setMealType( MealType mealType ) {
        this.mealType = mealType;
    }

    public List< Ingredient > getIngredients( ) {
        return ingredients;
    }

    public void setIngredients( List< Ingredient > ingredients ) {
        this.ingredients = ingredients;
    }

    private String name;
    @Column(length = 1000)
    private String description;
    private int timeRequired;
    @Enumerated(EnumType.STRING)
    private MealType mealType;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "recipe_id")
    private List< Ingredient > ingredients;

    public Long getId( ) {
        return id;
    }

    public void setId( Long id ) {
        this.id = id;
    }


}
