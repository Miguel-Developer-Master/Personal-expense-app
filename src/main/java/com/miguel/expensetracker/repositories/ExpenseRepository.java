package com.miguel.expensetracker.repositories;

import com.miguel.expensetracker.models.Expense;
import com.miguel.expensetracker.models.ReceiveExpense;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

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

    public Long getCategoryIdByExpenseId(long expense_id) {
        String SQL = "SELECT category_id FROM expenses WHERE id = ?";

        Long category_id = jdbcTemplate.queryForObject(SQL, Long.class, expense_id);

        return category_id;
    }

    public BigDecimal getAmountByExpenseId(long expense_id) {
        String SQL = "SELECT amount FROM expenses WHERE id = ?";

        BigDecimal amount = jdbcTemplate.queryForObject(SQL, BigDecimal.class, expense_id);

        return amount;
    }

    public BigDecimal getAlreadySpentAmount() {
        String SQL = "SELECT COALESCE(SUM(AMOUNT), 0) FROM EXPENSES";

        BigDecimal totalAmount = jdbcTemplate.queryForObject(SQL, BigDecimal.class);

        return totalAmount;
    }

    public BigDecimal getSpentMoneyInTheMonth() {
        String SQL = "SELECT COALESCE(SUM(AMOUNT), 0) FROM EXPENSES WHERE expense_data >= ? AND  expense_data < ?";

        LocalDate firstDayOfMonth = LocalDate.now().withDayOfMonth(1);
        LocalDate firstDayOfNextMonth = firstDayOfMonth.plusMonths(1);

        BigDecimal monthAmount = jdbcTemplate.queryForObject(SQL, BigDecimal.class, firstDayOfMonth, firstDayOfNextMonth);

        return monthAmount;
    }

    public BigDecimal getExpensesNumber() {
        String SQL = "SELECT COUNT(*) FROM expenses";

        BigDecimal expensesNumber = jdbcTemplate.queryForObject(SQL, BigDecimal.class);

        return expensesNumber;
    }

    public List<Expense> getAllExpenses() {
        String SQL = "SELECT * FROM expenses";
        List<Expense> expenses = jdbcTemplate.query(SQL, (result, rowNum) -> {
            Expense expense = new Expense();

            expense.id = result.getLong("id");
            expense.title = result.getString("title");
            expense.amount = result.getBigDecimal("amount");
            expense.description = result.getString("description");
            expense.expense_data = result.getObject("expense_data", LocalDate.class);
            expense.category_id = result.getLong("category_id");

            return expense;
        });

        return expenses;
    }

    public void deleteExpenseById(Long id) {
        String SQL = "DELETE FROM expenses WHERE id = ?";
        jdbcTemplate.update(SQL, id);
    }

    public Expense getEditExpenseById(Long id) {
        String SQL =  "SELECT * FROM expenses WHERE id = ?";
        Expense expense = jdbcTemplate.queryForObject(SQL, (result, rowNum) -> {
            Expense newExpense = new Expense();

            newExpense.id = result.getLong("id");
            newExpense.title = result.getString("title");
            newExpense.amount = result.getBigDecimal("amount");
            newExpense.description = result.getString("description");
            newExpense.expense_data = result.getObject("expense_data", LocalDate.class);
            newExpense.category_id = result.getLong("category_id");

            return newExpense;
        }, id);

        return expense;
    }

    public void updateExpenseById(Expense expense) {
        String SQL = """
                UPDATE expenses
                SET
                title = ?,
                amount = ?,
                description = ?,
                expense_data = ?,
                category_id = ?
                WHERE id = ?
        """;

        jdbcTemplate.update(SQL, expense.title, expense.amount, expense.description, expense.expense_data, expense.category_id, expense.id);
    }

    public String getFormattedExpenseDateByExpenseId(Long expense_id) {
        String SQL = "SELECT expense_data FROM expenses WHERE id = ?";
        LocalDate localDate = jdbcTemplate.queryForObject(SQL, LocalDate.class, expense_id);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy MMM dd", Locale.ENGLISH);

        String formattedDate = localDate.format(formatter);

        return formattedDate;
    }
}
