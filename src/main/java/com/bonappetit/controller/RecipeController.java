package com.bonappetit.controller;

import com.bonappetit.model.dto.AddRecipeDto;
import com.bonappetit.model.entity.enums.CategoryName;
import com.bonappetit.service.RecipeService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @ModelAttribute("recipeData")
    public AddRecipeDto addRecipeDto() {
        return new AddRecipeDto();
    }

    @GetMapping("/recipe-add")
    public String viewAddRecipe(Model model) {
        model.addAttribute("categories", CategoryName.values());
        return "recipe-add";
    }

    @PostMapping("/recipe-add")
    public String doAddRecipe(
            @Valid AddRecipeDto addRecipeDto,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("recipeData", addRecipeDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.recipeData", bindingResult);
            return "redirect:recipe-add";
        }

        if (!recipeService.addRecipe(addRecipeDto)) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.recipeData", bindingResult);
            redirectAttributes.addFlashAttribute("mustBeLoggedIn", true);
        }


        return "redirect:home";
    }
}
