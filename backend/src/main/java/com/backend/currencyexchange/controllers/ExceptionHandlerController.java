package com.backend.currencyexchange.controllers;

import com.backend.currencyexchange.dtos.helpers.ErrorMessage;
import com.backend.currencyexchange.exceptions.CurrencyExchangeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Slf4j
@ControllerAdvice
public class ExceptionHandlerController {

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = {CurrencyExchangeException.class})
    public @ResponseBody ErrorMessage handleCurrencyExchangeException(Exception e) {
        UUID errorId = UUID.randomUUID();
        log.error(String.format("[%s] %s", errorId, e.getMessage()));
        return new ErrorMessage(errorId, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), new SimpleDateFormat("yyyy.MM.dd HH:mm:ss.SSS").format(new Date()));
    }
}
