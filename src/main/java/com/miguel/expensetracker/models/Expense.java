package com.miguel.expensetracker.models;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Expense {

    public long id;
    public String title;
    public BigDecimal amount;
    public String description;
    public LocalDate expense_data;
    public long category_id;
}
