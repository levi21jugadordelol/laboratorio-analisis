package com.laboratorio.analisis_clinico.personalMedico.application.exception;

public class PersonalNotFoundException extends RuntimeException{
    public PersonalNotFoundException(String message){
        super(message);
    }
}
