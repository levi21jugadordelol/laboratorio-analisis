package com.laboratorio.analisis_clinico.resultado.application.exception;

public class ResultadoNotFoundException extends RuntimeException{
    public ResultadoNotFoundException(String message){
        super(message);
    }
}
