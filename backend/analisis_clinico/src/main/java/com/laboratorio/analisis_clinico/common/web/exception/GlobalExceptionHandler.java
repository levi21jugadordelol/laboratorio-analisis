package com.laboratorio.analisis_clinico.common.web.exception;

import com.laboratorio.analisis_clinico.common.domain.exception.DomainException;
import com.laboratorio.analisis_clinico.common.web.ErrorResponse;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ChangeSetPersister.NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleNotFound(ChangeSetPersister.NotFoundException ex) {
        return ErrorResponse.from(ex, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DomainException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleDomain(DomainException ex) {
        return ErrorResponse.from(ex, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleBadRequest(IllegalArgumentException ex) {
        return ErrorResponse.from(ex, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleGeneral(Exception ex) {
        return ErrorResponse.internal();
    }
}

