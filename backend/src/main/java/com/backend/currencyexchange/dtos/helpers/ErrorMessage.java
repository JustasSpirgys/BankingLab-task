package com.backend.currencyexchange.dtos.helpers;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@AllArgsConstructor
@Data
public class ErrorMessage {
    UUID errorId;
    String errorMessage;
    int status;
    String timestamp;
}
