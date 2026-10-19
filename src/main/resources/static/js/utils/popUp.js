import {changePage} from "./changePage.js";

export async function deleteExpense(id) {
    const answer = confirm('Are you sure you want to delete this expense?');
    if (answer) {
        await fetch(`/expenses/delete-expense-by-id/${id}`, {
            method: 'DELETE'
        });

        changePage("");
    }
}

export async function deleteCategory(id) {
    const answer = confirm("Are you sure you want to delete this category? You will delete all expenses related with this category too");
    if (answer) {
        await fetch(`/categories/delete-category-and-expenses-by-category-id/${id}`, {
            method: 'DELETE'
        });

        changePage("");
    }
}