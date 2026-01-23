package com.laboratorio.analisis_clinico.ordenAnalisis.adapter.in.web.exception;


import com.laboratorio.analisis_clinico.common.web.ErrorResponse;
import com.laboratorio.analisis_clinico.ordenAnalisis.application.exception.OrdenAnalisisNotFoundException;
import com.laboratorio.analisis_clinico.ordenAnalisis.domain.exception.OrdenAnalisisDomainException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

public class OrdenAnalisisExceptionHandler {
    @ExceptionHandler(OrdenAnalisisNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleNotFound(OrdenAnalisisNotFoundException ex) {
        return ErrorResponse.from(ex, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(OrdenAnalisisDomainException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleDomain(OrdenAnalisisDomainException ex) {
        return ErrorResponse.from(ex, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleGeneral(Exception ex) {
        return ErrorResponse.internal();
    }
}
