package org.geckotech.recipesbook.ingredient;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity

public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String amount;

    public Long getId( ) {
        return id;
    }

    public void setId( Long id ) {
        this.id = id;
    }

    public String getName( ) {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }

    public String getAmount( ) {
        return amount;
    }

    public void setAmount( String amount ) {
        this.amount = amount;
    }
}
