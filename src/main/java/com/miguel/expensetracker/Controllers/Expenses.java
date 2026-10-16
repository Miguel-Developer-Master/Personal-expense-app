package com.miguel.expensetracker.Controllers;

import com.miguel.expensetracker.models.ReceiveExpense;
import com.miguel.expensetracker.repositories.ExpenseRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
}
