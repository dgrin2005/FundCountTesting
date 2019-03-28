function prepareRequestBody() {
    return JSON.stringify({
        date: document.getElementById("date").value,
        amount: document.getElementById("amount").value
    });
}

function checkData() {
    if (/^\d{4}[\/\-](0?[1-9]|1[012])[\/\-](0?[1-9]|[12][0-9]|3[01])$/.test(document.getElementById("date").value)) {
        if (/^[0-9]*[.,]?[0-9]+$/.test(document.getElementById("amount").value)) {
            return true;
        } else {
            displayError("wrong amount");
            return false;
        }
    } else {
        displayError("wrong date");
        return false;
    }
}