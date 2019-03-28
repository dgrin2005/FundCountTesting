package ru.trustsoft.FundCountTesting.Service.Impl;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.trustsoft.FundCountTesting.Config.FixerApiProperties;
import ru.trustsoft.FundCountTesting.Exception.CurrencyRateException;
import ru.trustsoft.FundCountTesting.Service.CurrencyRateService;

@Service
public class CurrencyRateServiceImpl implements CurrencyRateService {

    private static final Logger log = LoggerFactory.getLogger(CurrencyRateServiceImpl.class);

    private final FixerApiProperties fixerApiProperties;

    @Autowired
    public CurrencyRateServiceImpl(FixerApiProperties fixerApiProperties) {
        this.fixerApiProperties = fixerApiProperties;
    }

    @Override
    public double getCurrencyProfit(String date, double amount) throws CurrencyRateException {
        return (getSaleRate() - getPurchaseRate(date) - FixerApiProperties.spread) * amount;
    }

    private double getPurchaseRate(String date) throws CurrencyRateException {
        return getRateFromApi(fixerApiProperties.getUrl() + date +
                "?access_key=" + fixerApiProperties.getKey() +
                "&base=" + FixerApiProperties.base +
                "&symbols=" + FixerApiProperties.cur);
    }

    private double getSaleRate() throws CurrencyRateException {
        return getRateFromApi(fixerApiProperties.getUrl() + "/latest" +
                "?access_key=" + fixerApiProperties.getKey() +
                "&base=" + FixerApiProperties.base +
                "&symbols=" + FixerApiProperties.cur);
    }

    private double getRateFromApi(String url) throws CurrencyRateException {
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(url, String.class);
        JsonObject jsonObject = new JsonParser().parse(response).getAsJsonObject();
        log.info(jsonObject.toString());
        if (jsonObject.get("rates") == null) {
            throw new CurrencyRateException("Bad request");
        }
        return jsonObject.get("rates").getAsJsonObject().get(FixerApiProperties.cur).getAsDouble();
    }
}
