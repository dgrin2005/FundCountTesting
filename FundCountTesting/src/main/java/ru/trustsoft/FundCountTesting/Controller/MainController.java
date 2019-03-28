package ru.trustsoft.FundCountTesting.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.trustsoft.FundCountTesting.Config.FixerApiProperties;
import ru.trustsoft.FundCountTesting.Dto.CurrencyRateDto;
import ru.trustsoft.FundCountTesting.Exception.CurrencyRateException;
import ru.trustsoft.FundCountTesting.Service.CurrencyRateService;

@RestController
@RequestMapping("fixerapi")
public class MainController {
    private static final Logger log = LoggerFactory.getLogger(MainController.class);

    private final CurrencyRateService currencyRateService;

    @Autowired
    public MainController(CurrencyRateService currencyRateService) {
        this.currencyRateService = currencyRateService;
    }

    @RequestMapping(value = "getCurrencyProfit", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> getCurrencyProfit(@RequestBody CurrencyRateDto currencyRateDto) throws CurrencyRateException {
        ResponseEntity<String> response;
        try {
            log.info(currencyRateDto.toString());
            double rate = currencyRateService.getCurrencyProfit(currencyRateDto.getDate(), currencyRateDto.getAmount());
            response = new ResponseEntity<>(String.valueOf(rate), HttpStatus.OK);
        } catch (Exception e) {
            throw new CurrencyRateException(e);
        }
        return response;
    }

    @RequestMapping(value = "getBaseCurrency", method = RequestMethod.GET)
    public ResponseEntity<String> getBaseCurrency() {
        return new ResponseEntity<>(FixerApiProperties.base, HttpStatus.OK);
    }

    @RequestMapping(value = "getCurrentCurrency", method = RequestMethod.GET)
    public ResponseEntity<String> getCurrentCurrency() {
        return new ResponseEntity<>(FixerApiProperties.cur, HttpStatus.OK);
    }

}
