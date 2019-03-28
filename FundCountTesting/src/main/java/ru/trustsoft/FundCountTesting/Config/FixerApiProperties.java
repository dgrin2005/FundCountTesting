package ru.trustsoft.FundCountTesting.Config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "fixerapi")
public class FixerApiProperties {

    private String url;
    private String key;

    //public static String base = "RUB";
    public static String base = "EUR";
    public static String cur = "USD";
    //public static double spread = 0.64;
    public static double spread = 0.01;

}
