function recalculate() {
    hideError();
    if (checkData()) {
        httpPostAsync(API_ADDRESS + "/getCurrencyProfit", prepareRequestBody(), function (response) {
            displaySum(response)
        });
    }
}