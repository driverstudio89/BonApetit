package com.bonappetit.init;

import com.bonappetit.model.entity.Category;
import com.bonappetit.model.entity.enums.CategoryName;
import com.bonappetit.repo.CategoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class InitCategories implements CommandLineRunner {

    private final Map<CategoryName, String> categoryDescriptions = Map.of(
            CategoryName.MAIN_DISH, "Heart of the meal, substantial and satisfying; main dish delights taste buds.",
            CategoryName.DESSERT, "Sweet finale, indulgent and delightful; dessert crowns the dining experience with joy.",
            CategoryName.COCKTAIL, "Sip of sophistication, cocktails blend flavors, creating a spirited symphony in every glass."
    );

    private final CategoryRepository categoryRepository;

    public InitCategories(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        if (categoryRepository.count() > 0) {
            return;
        }

        for (CategoryName categoryName : categoryDescriptions.keySet()) {
            Category category = new Category(categoryName, categoryDescriptions.get(categoryName));
            categoryRepository.save(category);
        }
    }
}
