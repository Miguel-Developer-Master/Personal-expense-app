package com.miguel.expensetracker.Controllers;

import com.miguel.expensetracker.models.Expense;
import com.miguel.expensetracker.models.ReceiveExpense;
import com.miguel.expensetracker.repositories.ExpenseRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/expenses/get-category-by-expense-id")
    public Long getCategoryIdByExpenseId(long expense_id) {
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
}
