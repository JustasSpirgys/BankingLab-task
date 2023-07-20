package com.backend.currencyexchange.services;

import com.backend.currencyexchange.dtos.CurrencyConversionDTO;

import java.math.BigDecimal;
import java.util.List;

public interface CurrencyExchangeService {

    List<String> getCurrencyList();
    String convertCurrencies(CurrencyConversionDTO currencyConversionDTO);
}
