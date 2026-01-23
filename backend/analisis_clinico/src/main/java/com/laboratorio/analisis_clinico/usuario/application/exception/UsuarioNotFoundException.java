package com.laboratorio.analisis_clinico.usuario.application.exception;

public class UsuarioNotFoundException extends RuntimeException{
    public UsuarioNotFoundException(String message){
        super(message);

    }
}
