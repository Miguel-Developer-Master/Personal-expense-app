package com.miguel.expensetracker.Controllers;

import com.miguel.expensetracker.models.Category;
import com.miguel.expensetracker.repositories.CategoryRepository;
import org.springframework.web.bind.annotation.*;

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

    @DeleteMapping("/categories/delete-category-and-expenses-by-category-id/{category_id}")
    public void deleteCategoryAndExpensesByCategoryId(@PathVariable Long category_id) {
        categoryRepository.deleteCategoryAndExpensesByCategoryId(category_id);
    }

    @PutMapping("/categories/edit-category-name/{category_id}")
    public void editCategoryName(@PathVariable Long category_id, @RequestBody String category_name) {
        categoryRepository.updateCategoryNameByCategoryId(category_id, category_name);
    }
}
