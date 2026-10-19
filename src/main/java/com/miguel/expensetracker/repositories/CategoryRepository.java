package com.miguel.expensetracker.repositories;

import com.miguel.expensetracker.models.Category;
import org.springframework.jdbc.IncorrectResultSetColumnCountException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public class CategoryRepository {

    private final JdbcTemplate jdbcTemplate;

    public CategoryRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void createCategory(String categoryName) {
        String SQL = "INSERT INTO categories (name) VALUES (?)";
        jdbcTemplate.update(SQL, categoryName);
    }

    public List<Category> getAllCategories() {
        String SQL = "SELECT * FROM categories";
        List<Category> categories = jdbcTemplate.query(SQL, (result, rowNum) -> {
            Category category = new Category();

            category.id = result.getLong("id");
            category.name = result.getString("name");

            return category;
        });

        return categories;
    }

    public Long getExpensesByCategoryId(long categoryId) {
        String SQL = "SELECT COUNT(*) FROM expenses WHERE category_id = ?";

        Long count = jdbcTemplate.queryForObject(SQL, Long.class, categoryId);

        return count;
    }

    public String getCategoryNameWithCategoryId(Long categoryId) {
        String SQL = "SELECT name FROM categories WHERE id = ?";

        String name = jdbcTemplate.queryForObject(SQL, String.class, categoryId);

        return name;
    }

    public void deleteCategoryAndExpensesByCategoryId(Long category_id) {
        String categoryDeleteSQL = "DELETE FROM categories WHERE id = ?";
        String expenseDeleteSQL = "DELETE FROM expenses WHERE category_id = ?";

        jdbcTemplate.update(expenseDeleteSQL, category_id);
        jdbcTemplate.update(categoryDeleteSQL, category_id);
    }

    public void updateCategoryNameByCategoryId(Long categoryId, String categoryName) {

        String SQL = "UPDATE categories SET name = ? WHERE id = ?";

        jdbcTemplate.update(SQL, categoryName, categoryId);
    }
}
