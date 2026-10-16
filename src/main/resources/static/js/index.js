import {changePage} from './utils/changePage.js';

const createCategoryButton = document.querySelector("#add-category");
const createExpenseButton = document.querySelector("#add-expense-button");

createCategoryButton.addEventListener("click", () => changePage("creating-category"));
createExpenseButton.addEventListener("click", () => changePage("creating-expense"));