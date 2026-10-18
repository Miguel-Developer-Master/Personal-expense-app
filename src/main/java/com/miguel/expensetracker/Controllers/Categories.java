package com.miguel.expensetracker.Controllers;

import com.miguel.expensetracker.models.Category;
import com.miguel.expensetracker.repositories.CategoryRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @GetMapping("/categories/get-all")
    public List<Category> getCategories() {
        return categoryRepository.getAllCategories();
    }

    @GetMapping("/categories/get-expenses-by-category-id/{category_id}")
    public Long getExpensesByCategoryId(@PathVariable Long category_id) {
        return  categoryRepository.getExpensesByCategoryId(category_id);
    }

    @GetMapping("/categories/get-category-name-with-category-id/{id}")
    public String getCategoryName(@PathVariable Long id) {
        return categoryRepository.getCategoryNameWithCategoryId(id);
    }
}
