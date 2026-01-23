package com.laboratorio.analisis_clinico.orden.application.exception;

public class OrdenNotFoundException extends RuntimeException{
    public OrdenNotFoundException(String message) {
        super(message);
    }
}
