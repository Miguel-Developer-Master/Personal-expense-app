package com.miguel.expensetracker.repositories;

import com.miguel.expensetracker.models.ReceiveExpense;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ExpenseRepository {

    private final JdbcTemplate jdbcTemplate;
    public ExpenseRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void createExpense(ReceiveExpense expense) {
        String SQL = "INSERT INTO expenses (title, amount, description, expense_data, category_id) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(SQL, expense.title, expense.amount, expense.description, expense.expense_data, expense.category_id);
    }
}
