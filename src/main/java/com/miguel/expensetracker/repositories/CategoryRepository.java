package com.miguel.expensetracker.repositories;

import com.miguel.expensetracker.models.Category;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

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


}
