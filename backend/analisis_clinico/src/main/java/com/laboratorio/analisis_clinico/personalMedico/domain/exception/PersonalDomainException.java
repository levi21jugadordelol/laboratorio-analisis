package com.laboratorio.analisis_clinico.personalMedico.domain.exception;

public class PersonalDomainException extends RuntimeException{
    public PersonalDomainException(String message){
        super(message);
    }
}
