package com.bonappetit.service;

import com.bonappetit.config.UserSession;
import com.bonappetit.model.dto.AddRecipeDto;
import com.bonappetit.model.entity.Category;
import com.bonappetit.model.entity.Recipe;
import com.bonappetit.model.entity.User;
import com.bonappetit.repo.CategoryRepository;
import com.bonappetit.repo.RecipeRepository;
import com.bonappetit.repo.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class RecipeService {

    private final RecipeRepository recipeRepository;
    private final UserSession userSession;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    public RecipeService(RecipeRepository recipeRepository, UserSession userSession, UserRepository userRepository, CategoryRepository categoryRepository) {
        this.recipeRepository = recipeRepository;
        this.userSession = userSession;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
    }


    public boolean addRecipe(AddRecipeDto addRecipeDto) {

        if (!userSession.isLoggedIn()) {
            return false;
        }

        User user = userRepository.findById(userSession.getId()).get();
        Category category = categoryRepository.findByCategoryName(addRecipeDto.getCategory());

        Recipe recipe = new Recipe();
        recipe.setName(addRecipeDto.getName());
        recipe.setIngredients(addRecipeDto.getIngredients());
        recipe.setAddedBy(user);
        recipe.setCategory(category);
        recipeRepository.save(recipe);
        return true;
    }
}
