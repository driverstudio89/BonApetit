package com.bonappetit.controller;

import com.bonappetit.config.UserSession;
import com.bonappetit.model.dto.RecipeInfoDto;
import com.bonappetit.model.entity.Recipe;
import com.bonappetit.model.entity.enums.CategoryName;
import com.bonappetit.service.RecipeService;
import com.bonappetit.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;

@Controller
public class HomeController {

    private final RecipeService recipeService;
    private final UserSession userSession;
    private final UserService userService;

    public HomeController(RecipeService recipeService, UserSession userSession, UserService userService) {
        this.recipeService = recipeService;
        this.userSession = userSession;
        this.userService = userService;
    }

    @GetMapping("/index")
    public String index() {
        if (userSession.isLoggedIn()) {
            return "redirect:/home";
        }
        return "index";
    }

    @GetMapping("/")
    public String nonLoggedIndex() {
        if (userSession.isLoggedIn()) {
            return "redirect:/home";
        }
        return "index";
    }

    @GetMapping("/home")
    @Transactional
    public String loggedInIndex(Model model) {
        if (!userSession.isLoggedIn()) {
            return "redirect:/";
        }

        Map<CategoryName, List<Recipe>> allRecipes = recipeService.findAllByCategory();

        List<RecipeInfoDto> favorites = userService.findFavorites(userSession.getId()).stream().map(RecipeInfoDto::new).toList();


        List<RecipeInfoDto> mainDishes = allRecipes.get(CategoryName.MAIN_DISH).stream().map(RecipeInfoDto::new).toList();
        List<RecipeInfoDto> desserts = allRecipes.get(CategoryName.DESSERT).stream().map(RecipeInfoDto::new).toList();
        List<RecipeInfoDto> cocktails = allRecipes.get(CategoryName.COCKTAIL).stream().map(RecipeInfoDto::new).toList();

        model.addAttribute("mainDishesData", mainDishes);
        model.addAttribute("dessertsData", desserts);
        model.addAttribute("cocktailsData", cocktails);
        model.addAttribute("favorites", favorites);
        System.out.println();
        return "home";
    }

}

