import {changePage} from './utils/changePage.js';
import * as popUp from './utils/popUp.js';

const createCategoryButton = document.querySelector("#add-category");
const createExpenseButton = document.querySelector("#add-expense-button");

createCategoryButton.addEventListener("click", () => changePage("creating-category"));
createExpenseButton.addEventListener("click", () => changePage("creating-expense"));


//Adding a feature that shows what you spend this month, what you already spent, and how much expenses you already have
const spentMoney = document.querySelector("#spent-money");
const monthSpentMoney = document.querySelector("#month-spent-money");
const totalExpenses = document.querySelector("#expenses");


async function interactiveDashboard() {

    const moneyAlreadySpent = await fetch("/expenses/get-already-spent-amount");
    const response = await moneyAlreadySpent.text();
    const alreadySpentMoney = Number(response);

    const monthResponse = await fetch("/expenses/get-month-spent-money");
    const response2 = await monthResponse.text();
    const monthSpentMoneyResponse = Number(response2);

    const expensesResponse = await fetch("/expenses/get-expenses-number");
    const response3 = await expensesResponse.text();
    const expensesNumber = Number(response3);


    spentMoney.textContent = `$ ${alreadySpentMoney}`;
    monthSpentMoney.textContent =  `$ ${monthSpentMoneyResponse}`
    totalExpenses.textContent =  `${expensesNumber}`
}

await interactiveDashboard();


//adding a feature that shows categories you have
const categoriesDiv = document.querySelector("#categories-div");

async function showCategoriesOnScreen() {
    const response = await fetch("/categories/get-all");
    const categories = await response.json();

    //sorry for the comments in this section, the code is confusing then I made them to avoid misunderstandings
    for (const category of categories) {

        //div for each category
        const newDiv = document.createElement("div");
        newDiv.classList.add("categories-divs");
        newDiv.id = category.id;

        // category info div
        const categoryInfoDiv = document.createElement("div");
        categoryInfoDiv.classList.add("category-info");

        // buttons div
        const categoryActionDiv = document.createElement("div");
        categoryActionDiv.classList.add("category-actions");

        // making category info div content
        const categoryTitle = document.createElement("h3");
        categoryTitle.textContent = `${category.name}`;

        const response = await fetch(`/categories/get-expenses-by-category-id/${category.id}`);
        const categoryExpenses = await response.text();

        const categoryTitleInfo = document.createElement("span");
        categoryTitleInfo.textContent = `${categoryExpenses} expenses`;

        categoryInfoDiv.append(categoryTitle);
        categoryInfoDiv.append(categoryTitleInfo);

        //making buttons div content
        const categoryEditButton = document.createElement("button");
        categoryEditButton.classList.add("edit-category");
        categoryEditButton.classList.add("buttons");
        categoryEditButton.innerHTML =  `<svg xmlns="http://www.w3.org/2000/svg" width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="1.8" stroke-linecap="round" stroke-linejoin="round"><path d="m16 4 4 4"/><path d="M3 21l4.5-1 12-12a2.12 2.12 0 0 0-3-3l-12 12L3 21z"/></svg>`

        const categoryDeleteButton = document.createElement("button");
        categoryDeleteButton.classList.add("delete-category");
        categoryDeleteButton.classList.add("buttons");
        categoryDeleteButton.innerHTML = `<svg xmlns="http://www.w3.org/2000/svg" width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="1.8" stroke-linecap="round" stroke-linejoin="round"><path d="M3 6h18"/><path d="M8 6V4h8v2"/><path d="M19 6l-1 14H6L5 6"/><path d="M10 11v6"/><path d="M14 11v6"/></svg>`

        categoryActionDiv.append(categoryEditButton);
        categoryActionDiv.append(categoryDeleteButton);

        //appending divs

        newDiv.append(categoryInfoDiv);
        newDiv.append(categoryActionDiv);

        categoriesDiv.append(newDiv);

        //making actions useful

        categoryDeleteButton.addEventListener("click", (event) => {
            const categoryId = event.target.closest(".categories-divs").id;
            popUp.deleteCategory(categoryId);
        });

        categoryEditButton.addEventListener("click", (event) => {
            const categoryId = event.target.closest(".categories-divs").id;
            changePage(`editing-category?id=${categoryId}`);
        });
    }

}

await showCategoriesOnScreen();



//making expenses visible
const tableBody = document.querySelector("#table-body");

async function showExpensesOnScreen() {
    const response = await fetch("/expenses/get-all-expenses");
    const expenses = await response.json();

    for (const expense of expenses) {

        //cell that expenses will be
        const newCell = document.createElement("tr");
        newCell.classList.add("expenses-table-row");
        newCell.id = expense.id;

        //getting category name based on category id
        const response = await fetch(`/categories/get-category-name-with-category-id/${expense.category_id}`);
        const categoryName = await response.text();


        //getting formatted data based on expense id
        const dateResponse = await fetch(`/expenses/get-formatted-expense-date-by-id/${expense.id}`);
        const date = await dateResponse.text();

        const expenseDateCell = document.createElement("td");
        expenseDateCell.textContent = date;

        const expenseNameCell = document.createElement("td");
        expenseNameCell.textContent = `${expense.title}`;


        const categoryNameCell = document.createElement("td");
        categoryNameCell.textContent = `${categoryName}`;


        const expenseAmountCell = document.createElement("td");
        expenseAmountCell.textContent = `${expense.amount}`;


        const expenseActionsCell = document.createElement("td");
        expenseActionsCell.classList.add("expense-actions");

        const expenseEditButton = document.createElement("button");
        expenseEditButton.classList.add("edit-expense-button");
        expenseEditButton.classList.add("buttons");
        expenseEditButton.innerHTML = `<svg xmlns="http://www.w3.org/2000/svg" width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="1.8" stroke-linecap="round" stroke-linejoin="round"><path d="m16 4 4 4"/><path d="M3 21l4.5-1 12-12a2.12 2.12 0 0 0-3-3l-12 12L3 21z"/></svg>`;

        const expenseDeleteButton = document.createElement("button");
        expenseDeleteButton.classList.add("delete-expense-button");
        expenseDeleteButton.classList.add("buttons");
        expenseDeleteButton.innerHTML = `<svg xmlns="http://www.w3.org/2000/svg" width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="1.8" stroke-linecap="round" stroke-linejoin="round"><path d="M3 6h18"/><path d="M8 6V4h8v2"/><path d="M19 6l-1 14H6L5 6"/><path d="M10 11v6"/><path d="M14 11v6"/></svg>`;

        //appending everything
        expenseActionsCell.append(expenseEditButton);
        expenseActionsCell.append(expenseDeleteButton);

        newCell.append(expenseDateCell);
        newCell.append(expenseNameCell);
        newCell.append(categoryNameCell);
        newCell.append(expenseAmountCell);
        newCell.append(expenseActionsCell)


        tableBody.append(newCell);

        //making buttons work
        expenseDeleteButton.addEventListener("click", (event) => {
            const expenseId = event.target.closest(".expenses-table-row").id;
            popUp.deleteExpense(expenseId);
        });

        expenseEditButton.addEventListener("click", (event) => {
            const expenseId = event.target.closest(".expenses-table-row").id;
            changePage(`editing-expense?id=${expenseId}`);
        })
    }
}

await showExpensesOnScreen();

//making about ume button work
const aboutUsButton = document.querySelector("#about-me");
const footer = document.querySelector("footer");

aboutUsButton.addEventListener("click", () => {
    footer.scrollIntoView({
        behavior: "smooth",
        block: "start"
    });
});