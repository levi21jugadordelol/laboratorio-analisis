package com.laboratorio.analisis_clinico.analisis.adapter.in.web.exception;




import com.laboratorio.analisis_clinico.analisis.application.exception.AnalisisNotFoundException;
import com.laboratorio.analisis_clinico.analisis.domain.exception.AnalisisDomainException;
import com.laboratorio.analisis_clinico.common.web.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class AnalisisExceptionHandler {

    @ExceptionHandler(AnalisisNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleNotFound(AnalisisNotFoundException ex) {
        return ErrorResponse.from(ex, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AnalisisDomainException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleDomain(AnalisisDomainException ex) {
        return ErrorResponse.from(ex, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleGeneral(Exception ex) {
        return ErrorResponse.internal();
    }
}
