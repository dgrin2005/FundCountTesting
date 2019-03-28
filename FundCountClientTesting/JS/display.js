function displaySum(sum) {
    if (sum < 0) {
        document.getElementById("profitLabel").innerHTML = "Loss";
    } else {
        document.getElementById("profitLabel").innerHTML = "Profit";
    }
    document.getElementById("profit").value = sum;
}

function displayCurrency(cur, curId) {
    document.getElementById(curId).innerHTML = cur;
}

function displayError(message) {
    document.getElementById("error").innerHTML = 'Error: ' + message;
}

function hideError() {
    document.getElementById("error").innerHTML = '';
}