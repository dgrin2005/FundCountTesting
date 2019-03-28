package ru.trustsoft.FundCountTesting.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CurrencyRateDto {
    private String date;
    private double amount;
}
