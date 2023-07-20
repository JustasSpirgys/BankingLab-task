package com.backend.currencyexchange.services;

import com.backend.currencyexchange.dtos.CurrencyConversionDTO;
import com.backend.currencyexchange.exceptions.CurrencyExchangeException;
import com.backend.currencyexchange.utils.CSVReaderUtil;
import com.opencsv.exceptions.CsvValidationException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CurrencyExchangeServiceImplTest {

    @Spy
    @InjectMocks
    private CurrencyExchangeServiceImpl currencyExchangeService;
    @Mock
    private CSVReaderUtil csvReader;

    private HashMap<String, BigDecimal> currencyExchangeRates;

    @BeforeEach
    public void setUp() {
        currencyExchangeRates = new HashMap<>();
        currencyExchangeRates.put("FEK", new BigDecimal("0.01"));
        currencyExchangeRates.put("USD", new BigDecimal("0.05"));
        currencyExchangeRates.put("EUR", new BigDecimal("0.1"));
        currencyExchangeRates.put("GBP", new BigDecimal("0.2"));
        currencyExchangeRates.put("ETH", new BigDecimal("0.25"));
        currencyExchangeRates.put("BTC", new BigDecimal("0.5"));
    }

    @Test
    public void testGetCurrencyList() throws CsvValidationException, IOException {
        when(csvReader.read(anyString())).thenReturn(currencyExchangeRates);
        List<String> currencyList = currencyExchangeService.getCurrencyList();
        assertEquals(currencyExchangeRates.keySet().size(), currencyList.size());
    }

    @Test
    public void testConvertCurrencies() throws CsvValidationException, IOException {
        when(csvReader.read(anyString())).thenReturn(currencyExchangeRates);
        CurrencyConversionDTO dto = new CurrencyConversionDTO(new BigDecimal("1"), "USD", "EUR");
        String result = currencyExchangeService.convertCurrencies(dto);
        assertEquals("1 USD = 0.5 EUR", result);
    }

    @Test
    public void testGetCurrencyListThrowsException() throws CsvValidationException, IOException {
        when(csvReader.read(anyString())).thenThrow(CurrencyExchangeException.class);
        assertThrows(CurrencyExchangeException.class, () -> currencyExchangeService.getCurrencyList());
    }

    @Test
    public void testConvertCurrenciesThrowsException() throws CsvValidationException, IOException {
        when(csvReader.read(anyString())).thenThrow(CurrencyExchangeException.class);
        CurrencyConversionDTO dto = new CurrencyConversionDTO(new BigDecimal("1"), "USD", "EUR");
        assertThrows(CurrencyExchangeException.class, () -> currencyExchangeService.convertCurrencies(dto));
    }
}