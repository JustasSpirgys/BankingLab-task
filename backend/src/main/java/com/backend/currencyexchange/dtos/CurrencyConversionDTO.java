package com.backend.currencyexchange.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class CurrencyConversionDTO {

    BigDecimal amount;
    String fromCurrency;
    String toCurrency;
}
