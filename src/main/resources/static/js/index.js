import {changePage} from './utils/changePage.js';

const createCategoryButton = document.querySelector("#add-category");

createCategoryButton.addEventListener("click", () => changePage("creating-category"))