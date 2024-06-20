package com.bonappetit.controller;

import com.bonappetit.config.UserSession;
import com.bonappetit.model.dto.AddRecipeDto;
import com.bonappetit.model.entity.enums.CategoryName;
import com.bonappetit.service.RecipeService;
import com.bonappetit.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class RecipeController {

    private final RecipeService recipeService;
    private final UserSession userSession;
    private final UserService userService;

    public RecipeController(RecipeService recipeService, UserSession userSession, UserService userService) {
        this.recipeService = recipeService;
        this.userSession = userSession;
        this.userService = userService;
    }

    @ModelAttribute("recipeData")
    public AddRecipeDto addRecipeDto() {
        return new AddRecipeDto();
    }



    @GetMapping("/recipe-add")
    public String viewAddRecipe(Model model) {
        if (!userSession.isLoggedIn()) {
            return "redirect:/";
        }
        model.addAttribute("categories", CategoryName.values());
        return "recipe-add";
    }

    @PostMapping("/recipe-add")
    public String doAddRecipe(
            @Valid AddRecipeDto addRecipeDto,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {
        if (!userSession.isLoggedIn()) {
            return "redirect:/";
        }

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("recipeData", addRecipeDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.recipeData", bindingResult);
            return "redirect:recipe-add";
        }

        if (!recipeService.addRecipe(addRecipeDto)) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.recipeData", bindingResult);
            redirectAttributes.addFlashAttribute("mustBeLoggedIn", true);
            return "redirect:recipe-add";
        }
        return "redirect:home";
    }

    @PostMapping("/add-to-favorites/{recipeId}")
    public String addToFavorites(@PathVariable long recipeId) {
        if (!userSession.isLoggedIn()) {
            return "redirect:/";
        }
        userService.addToFavorites(userSession.getId(), recipeId);

        return "redirect:/home";

    }



}
