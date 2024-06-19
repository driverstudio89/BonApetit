package com.bonappetit.model.entity;

import com.bonappetit.model.entity.enums.CategoryName;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CategoryName categoryName;

    @Column(nullable = false)
    private String description;

    @OneToMany(targetEntity = Recipe.class, mappedBy = "category")
    private List<Recipe> recipes;

    public Category() {

    }

    public Category(CategoryName categoryName, String description) {
        super();
        this.categoryName = categoryName;
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public CategoryName getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(CategoryName categoryName) {
        this.categoryName = categoryName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
    }
}
