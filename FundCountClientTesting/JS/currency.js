function loadCurrencies() {
    loadCurrency("BaseCurrency");
    loadCurrency("CurrentCurrency");
}

function loadCurrency(currency) {
    httpGetAsync(API_ADDRESS + "/get" + currency, function(response) {
        displayCurrency(response, currency)
    });
}
