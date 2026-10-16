package com.miguel.expensetracker.repositories;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

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

}
