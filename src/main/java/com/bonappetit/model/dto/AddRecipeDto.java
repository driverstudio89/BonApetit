package com.bonappetit.model.dto;

import com.bonappetit.model.entity.Category;
import com.bonappetit.model.entity.enums.CategoryName;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class AddRecipeDto {

    @NotNull
    @Size(min = 2, max = 40)
    private String name;

    @NotNull
    @Size(min = 2, max = 150)
    private String ingredients;

    @NotNull
    private CategoryName category;

    public @NotNull @Size(min = 2, max = 40) String getName() {
        return name;
    }

    public void setName(@NotNull @Size(min = 2, max = 40) String name) {
        this.name = name;
    }

    public @NotNull @Size(min = 2, max = 150) String getIngredients() {
        return ingredients;
    }

    public void setIngredients(@NotNull @Size(min = 2, max = 150) String ingredients) {
        this.ingredients = ingredients;
    }

    public @NotNull CategoryName getCategory() {
        return category;
    }

    public void setCategory(@NotNull CategoryName category) {
        this.category = category;
    }
}
