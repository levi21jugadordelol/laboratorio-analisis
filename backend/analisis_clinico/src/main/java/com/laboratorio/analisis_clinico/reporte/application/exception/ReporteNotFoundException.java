package com.laboratorio.analisis_clinico.reporte.application.exception;

public class ReporteNotFoundException extends RuntimeException{
    public ReporteNotFoundException(String message){
        super(message);
    }
}
