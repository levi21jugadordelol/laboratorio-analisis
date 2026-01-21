package com.laboratorio.analisis_clinico.analisis.application.exception;

public class AnalisisNotFoundException extends RuntimeException {
    public AnalisisNotFoundException(String message) {
        super(message);
    }
}
