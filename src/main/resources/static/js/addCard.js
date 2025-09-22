//get the form of adding new card
import {http} from "./http.js";

const addCardForm = document.getElementById("addCardForm");
// add a new card
const addCard = async (e) => {
    e.preventDefault();
    const cardHolder = document.getElementById("cardHolder").value;
    const pan = document.getElementById("pan").value;
    // make sure that PAN is 16 digits and is number, not string
    if (pan.length != 16 || !/^\d+$/.test(pan)) {
        alert("The PAN is invalid!");
        return;
    }

    try {
        const res = await http.post("/addCard", {pan: pan, cardHolder: cardHolder})
        console.log(res.data)
        if (res.data === 0) {
            alert("The PAN already exists!");
            return;
        } else {
            window.location.href = "http://localhost:8080/index.html";
        }
    } catch (e) {
        console.log("Fail to add the card:", e);
    }
}

// bind the add card form with add function, and runs when trigger submit action
addCardForm.addEventListener("submit", addCard);