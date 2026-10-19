import {changePage} from "./utils/changePage.js";

const mainTitle = document.querySelector("#main-title");
const button = document.querySelector("#submit");
const cancelButton = document.querySelector("#cancel");

cancelButton.addEventListener("click", () => changePage(""))

async function showCategory() {
    const param = new URLSearchParams(window.location.search);
    const id = param.get("id");

    const response = await fetch(`/categories/get-category-name-with-category-id/${id}`);
    const name = await response.text();
    console.log(name);

    mainTitle.value = name;
}

async function editCategory() {
    const param = new URLSearchParams(window.location.search);
    const id = param.get("id");

    await fetch(`/categories/edit-category-name/${id}`, {
        method: "PUT",
        headers: {"Content-Type": "text/plain"},
        body: mainTitle.value
    });
}

button.addEventListener("click", async () => {
    await editCategory();
    changePage("");
});

await showCategory();

