package ru.trustsoft.FundCountTesting.Service;

import ru.trustsoft.FundCountTesting.Exception.CurrencyRateException;

public interface CurrencyRateService {

    double getCurrencyProfit(String date, double amount) throws CurrencyRateException;
}
