package com.laboratorio.analisis_clinico.auditlog.application.exception;

public class AuditlogNotFoundException extends RuntimeException{
    public AuditlogNotFoundException(String message) {
        super(message);
    }
}
