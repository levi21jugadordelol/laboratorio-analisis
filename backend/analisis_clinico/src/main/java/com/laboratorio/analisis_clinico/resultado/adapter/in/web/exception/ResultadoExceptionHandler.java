package com.laboratorio.analisis_clinico.resultado.adapter.in.web.exception;


import com.laboratorio.analisis_clinico.common.web.ErrorResponse;
import com.laboratorio.analisis_clinico.resultado.application.exception.ResultadoNotFoundException;
import com.laboratorio.analisis_clinico.resultado.domain.exception.ResultadoDomainException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

public class ResultadoExceptionHandler {
    @ExceptionHandler(ResultadoNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleNotFound(ResultadoNotFoundException ex) {
        return ErrorResponse.from(ex, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ResultadoDomainException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleDomain(ResultadoDomainException ex) {
        return ErrorResponse.from(ex, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleGeneral(Exception ex) {
        return ErrorResponse.internal();
    }
}
