package com.bonappetit.model.dto;

import com.bonappetit.model.entity.Recipe;

public class RecipeInfoDto {

    private long id;

    private String name;

    private String ingredients;

    public RecipeInfoDto(Recipe r) {
        this.id = r.getId();
        this.name = r.getName();
        this.ingredients = r.getIngredients();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }
}
