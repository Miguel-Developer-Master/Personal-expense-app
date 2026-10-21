import {changePage} from "./utils/changePage.js";
import {isValidDate} from "./utils/validDate.js";

const mainForm = document.querySelector("#expense-form");

const title = document.querySelector('#expense-name');
const description = document.querySelector('#expense-description');
const date = document.querySelector('#expense-date');
const amount = document.querySelector('#expense-amount');
const select = document.querySelector('#expense-category');

async function putCategoriesIntoSelect() {

    const response = await fetch("categories/get-all");
    const categories = await response.json();

    for (const category of categories) {
        select.add(new Option(category.name, category.id));
    }
}



async function createExpense(event) {
    event.preventDefault();

    console.log("function loaded");


    if (title.value.length > 50 || title.value.trim().length < 1) {

        console.log("1");
        title.placeholder = "Please enter a valid title (bigger than 50, less then 0 chars)";
        title.textContent = "";

    } else if (description.value.length > 150) {

        console.log("2");
        description.placeholder = "Please enter a valid description (less then 150 chars)";
        description.textContent = "";

    } else if (amount.value.trim() === 0 || amount.value.length > 15 || !Number.isFinite(Number(amount.value))) {

        console.log("3");
        amount.textContent = "";
        amount.placeholder = "Please enter a valid amount";

    } else if (date.value.trim() === "" || !isValidDate(date.value)) {

        console.log("4");
        date.placeholder = "Please enter a valid date";
        amount.textContent = "";

    } else if (select.value === "") {

        console.log("5");
        description.placeholder = "Please select a valid category";
        select.textContent = "";

    } else {

        console.log("valid form")

        console.log("Title element:", title);
        console.log("Title value:", title.value);

        await fetch("/expenses/create", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({
                title: title.value,
                amount: amount.value,
                description: description.value,
                expense_data: date.value,
                category_id: select.value
            })
        });

        changePage("");

    }
}

mainForm.addEventListener("submit", createExpense);

await putCategoriesIntoSelect();