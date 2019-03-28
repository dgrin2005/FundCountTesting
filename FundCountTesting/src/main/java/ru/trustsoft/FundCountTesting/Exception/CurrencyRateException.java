package ru.trustsoft.FundCountTesting.Exception;

public class CurrencyRateException extends Exception {

    public CurrencyRateException(Exception e) {
        super(e);
    }

    public CurrencyRateException(String message) {
        super(message);
    }
}
