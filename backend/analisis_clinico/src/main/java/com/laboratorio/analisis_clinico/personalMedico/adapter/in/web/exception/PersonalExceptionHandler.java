package com.laboratorio.analisis_clinico.personalMedico.adapter.in.web.exception;


import com.laboratorio.analisis_clinico.common.web.ErrorResponse;
import com.laboratorio.analisis_clinico.personalMedico.application.exception.PersonalNotFoundException;
import com.laboratorio.analisis_clinico.personalMedico.domain.exception.PersonalDomainException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

public class PersonalExceptionHandler {
    @ExceptionHandler(PersonalNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleNotFound(PersonalNotFoundException ex) {
        return ErrorResponse.from(ex, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PersonalDomainException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleDomain(PersonalDomainException ex) {
        return ErrorResponse.from(ex, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleGeneral(Exception ex) {
        return ErrorResponse.internal();
    }
}
