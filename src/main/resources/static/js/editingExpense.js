import {changePage} from "./utils/changePage.js";
import {isValidDate} from "./utils/validDate.js";

const expenseName = document.getElementById("expense-name");
const expenseAmount = document.getElementById("expense-amount");
const expenseDate = document.getElementById("expense-date");
const expenseDescription = document.getElementById("expense-description");
const expenseCategorySelect = document.getElementById("expense-category");

const params = new URLSearchParams(window.location.search);
const id = params.get("id");


async function showingExpense() {
    const response = await fetch(`/expenses/get-edit-expense-by-id/${id}`);
    const expense = await response.json();

    const responseCategories = await fetch(`/categories/get-all`);
    const categories = await responseCategories.json();

    const responseExpenseCategory = await fetch(`/expenses/get-category-by-expense-id/${id}`);
    const expenseCategoryId = await responseExpenseCategory.json();

    for (const category of categories) {

        expenseCategorySelect.add(new Option(category.name, category.id));

        if (category.id === expenseCategoryId) {
            expenseCategorySelect.value = category.id;
            console.log(expenseCategorySelect.value);
        }

    }

    expenseName.value = expense.title;
    expenseAmount.value = expense.amount;
    expenseDate.value = expense.expense_data;
    expenseDescription.value = expense.description;
}

await showingExpense();

async function updateExpense() {

    if(
        expenseName.value.trim() === "" ||
        expenseAmount.value.trim() === "" ||
        (expenseDate.value.trim() === "" || isValidDate(expenseDate.value))
    ) {

        const newExpense = {
            id: id,
            title: expenseName.value,
            amount: expenseAmount.value,
            description: expenseDescription.value,
            expense_data: expenseDate.value,
            category_id: expenseCategorySelect.value
        }

    await fetch(`/expenses/update-expense-by-id`, {
        method: 'PUT',
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify(newExpense)
    });
    }
}

const form = document.querySelector("#edit-expense-form");
form.addEventListener("submit", async (event) => {
    event.preventDefault();
    await updateExpense();
    changePage("");
})