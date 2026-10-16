import {changePage} from "./utils/changePage.js";

const form = document.querySelector("#category-form");
const categoryNameInput = document.querySelector("#category-name");

async function addCategory(event) {
    event.preventDefault();

    if (categoryNameInput.value === "" || categoryNameInput.value.length > 50) {
        categoryNameInput.value = "";
        categoryNameInput.placeholder = "The category name has to be at maximum 50, and at least 0 characters length.";
        categoryNameInput.classList.add("invalid-category-name");
    } else {

        categoryNameInput.classList.remove("invalid-category-name");
        await fetch(`/categories/create/${categoryNameInput.value}`, {
            method: "POST",
            headers: {"Content-Type": "application/json"},
            body: JSON.stringify({
                name: categoryNameInput.value
            })
        });

        changePage("");

    }
}

form.addEventListener("submit", addCategory);