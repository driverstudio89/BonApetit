package com.bonappetit.service;

import com.bonappetit.model.entity.Category;
import com.bonappetit.repo.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> findAllCategory() {
        return categoryRepository.findAll();
    }


}
