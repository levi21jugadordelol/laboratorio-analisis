package com.laboratorio.analisis_clinico.ordenAnalisis.application.exception;

public class OrdenAnalisisNotFoundException extends RuntimeException{
    public OrdenAnalisisNotFoundException(String message){
        super(message);
    }
}
