package com.miguel.expensetracker.Controllers;

import com.miguel.expensetracker.models.Expense;
import com.miguel.expensetracker.models.ReceiveExpense;
import com.miguel.expensetracker.repositories.ExpenseRepository;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
public class Expenses {

    private final ExpenseRepository expenseRepository;
    public Expenses(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    @PostMapping("/expenses/create")
    public void createExpense(@RequestBody ReceiveExpense expense) {
        expenseRepository.createExpense(expense);
    }

    @GetMapping("/expenses/get-category-by-expense-id/{expense_id}")
    public Long getCategoryIdByExpenseId(@PathVariable Long expense_id) {
        return expenseRepository.getCategoryIdByExpenseId(expense_id);
    }

    @GetMapping("/expenses/get-amount-by-expense-id")
    public BigDecimal getAmountByExpenseId(long expense_id) {
        return expenseRepository.getAmountByExpenseId(expense_id);
    }

    @GetMapping("/expenses/get-already-spent-amount")
    public BigDecimal getAlreadySpentAmount() {
        return expenseRepository.getAlreadySpentAmount();
    }

    @GetMapping("/expenses/get-month-spent-money")
    public BigDecimal getMonthSpentMoney() {
        return expenseRepository.getSpentMoneyInTheMonth();
    }

    @GetMapping("/expenses/get-expenses-number")
    public BigDecimal getExpensesNumber() {
        return expenseRepository.getExpensesNumber();
    }

    @GetMapping("/expenses/get-all-expenses")
    public List<Expense> getAllExpenses() {
        return expenseRepository.getAllExpenses();
    }

    @DeleteMapping("/expenses/delete-expense-by-id/{id}")
    public void deleteExpenseById(@PathVariable Long id) {
        expenseRepository.deleteExpenseById(id);
    }

    @GetMapping("/expenses/get-edit-expense-by-id/{id}")
    public Expense getEditExpenseById(@PathVariable Long id) {
        return expenseRepository.getEditExpenseById(id);
    }

    @PutMapping("/expenses/update-expense-by-id")
    public void updateExpenseById(@RequestBody Expense expense) {
        expenseRepository.updateExpenseById(expense);
    }
}
