package com.backend.currencyexchange.services;

import com.backend.currencyexchange.dtos.CurrencyConversionDTO;
import com.backend.currencyexchange.exceptions.CurrencyExchangeException;
import com.backend.currencyexchange.utils.CSVReaderUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CurrencyExchangeServiceImpl implements CurrencyExchangeService {

    private final CSVReaderUtil csvReader;

    public List<String> getCurrencyList() {
        log.info("Retrieving currency list");
        return new ArrayList<>(readCurrencyExchangeRates("currencyExchangeRates.csv").keySet());
    }

    public String convertCurrencies(CurrencyConversionDTO currencyConversionDTO) {
        log.info("Converting currencies");
        HashMap<String, BigDecimal> currencyExchangeRates = readCurrencyExchangeRates("currencyExchangeRates.csv");
        BigDecimal fromCurrencyExchangeRate = currencyExchangeRates.get(currencyConversionDTO.getFromCurrency());
        BigDecimal toCurrencyExchangeRate = currencyExchangeRates.get(currencyConversionDTO.getToCurrency());
        try {
            return String.format("%s %s = %s %s",
                    currencyConversionDTO.getAmount().toPlainString(),
                    currencyConversionDTO.getFromCurrency(),
                    fromCurrencyExchangeRate
                            .divide(toCurrencyExchangeRate, new MathContext(18))
                            .multiply(currencyConversionDTO.getAmount(), new MathContext(18))
                            .toPlainString(),
                    currencyConversionDTO.getToCurrency());
        } catch (Exception e) {
            throw new CurrencyExchangeException(e.getMessage());
        }
    }

    HashMap<String, BigDecimal> readCurrencyExchangeRates(String fileName) {
        try {
            return csvReader.read(fileName);
        } catch (Exception e) {
            throw new CurrencyExchangeException(e.getMessage());
        }
    }
}
