package com.miguel.expensetracker.Controllers;

import com.miguel.expensetracker.repositories.CategoryRepository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Categories {

    private final CategoryRepository categoryRepository;

    public Categories(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @PostMapping("/categories/create/{categoryName}")
    public void createCategory(@PathVariable String categoryName) {
        categoryRepository.createCategory(categoryName);
    }
}
