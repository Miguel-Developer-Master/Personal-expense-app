package com.miguel.expensetracker.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/creating-category")
    public String creatingCategory() {
        return "category";
    }

    @GetMapping("/creating-expense")
    public String creatingExpense() {
        return "expense";
    }

    @GetMapping("/editing-expense")
    public String editingExpense() {
        return "editingExpense";
    }

    @GetMapping("/editing-category")
    public String editingCategory() {
        return "editingCategory";
    }
}
