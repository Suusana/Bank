import {http} from "./http.js";

// render the table to display the data
const renderTable = (cards) => {
    // get the parent element of inserting data
    const row = document.getElementById("Info");
    //clear current result
    row.innerHTML = "";
    // Travel the response from backend
    cards.map(card => {
        // replace the first 12 digts to *
        const tr = document.createElement("tr");
        tr.innerHTML = `
            <td>${card.id}</td>
            <td>${card.cardHolder}</td>
            <td>************${card.last4Digits}</td>
            <td>${card.createTime}</td>
            `
        row.appendChild(tr);
    })
}
// Load all the card from the database
const loadCards = async () => {
    try {
        const res = await http.get("/getAll");
        renderTable(res.data);
    } catch (e) {
        console.log("Fail to fetch data:", e);
    }
}
// when loading the page, try to fetch data from backend
window.onload = loadCards;

//search by the last 4 digits
const searchForm = document.getElementById("search");
const search = async (e) => {
    e.preventDefault();
    const keyword = document.getElementById("keyword").value;
    try {
        const res = await http.get("/search", {params: {keyword}});
        renderTable(res.data);
    } catch (e) {
        console.log("Fail to search results:", e);
    }
}
// bind the search form with search function, and runs when trigger submit action
searchForm.addEventListener("submit", search);