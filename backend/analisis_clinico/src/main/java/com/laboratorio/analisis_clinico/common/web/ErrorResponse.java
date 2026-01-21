package com.laboratorio.analisis_clinico.common.web;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class ErrorResponse {

    private final String message;
    private final int status;
    private final String error;
    private final LocalDateTime timestamp;

    private ErrorResponse(String message, int status, String error) {
        this.message = message;
        this.status = status;
        this.error = error;
        this.timestamp = LocalDateTime.now();
    }

    // ===== FACTORY METHODS =====

    public static ErrorResponse from(Exception ex, HttpStatus status) {
        return new ErrorResponse(
                ex.getMessage(),
                status.value(),
                status.getReasonPhrase()
        );
    }

    public static ErrorResponse internal() {
        return new ErrorResponse(
                "Error interno del servidor",
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()
        );
    }

    // ===== GETTERS =====

    public String getMessage() {
        return message;
    }

    public int getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}

