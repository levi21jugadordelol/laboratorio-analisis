package com.laboratorio.analisis_clinico.paciente.application.exception;

public class PacienteNotFoundException extends RuntimeException{
    public PacienteNotFoundException(String message){
        super(message);
    }
}
