package com.backend.currencyexchange.controllers;

import com.backend.currencyexchange.dtos.CurrencyConversionDTO;
import com.backend.currencyexchange.services.CurrencyExchangeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api")
public class CurrencyExchangeController {

    private final CurrencyExchangeService currencyExchangeService;

    @GetMapping(path = "/getCurrencyList")
    public ResponseEntity<List<String>> getCurrencyList() {
        return ResponseEntity.ok(currencyExchangeService.getCurrencyList());
    }

    @PostMapping(path = "/convertCurrencies")
    public ResponseEntity<String> convertCurrencies(@RequestBody CurrencyConversionDTO currencyConversionDTO) {
        return ResponseEntity.ok(currencyExchangeService.convertCurrencies(currencyConversionDTO));
    }
}
