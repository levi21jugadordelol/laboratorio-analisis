package com.laboratorio.analisis_clinico.paciente.domain.exception;

public class PacienteDomainException extends RuntimeException{
    public PacienteDomainException(String message){
        super(message);
    }
}
