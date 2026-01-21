package com.laboratorio.analisis_clinico.auditlog.adapter.in.web.exception;

import com.laboratorio.analisis_clinico.analisis.application.exception.AnalisisNotFoundException;
import com.laboratorio.analisis_clinico.analisis.domain.exception.AnalisisDomainException;
import com.laboratorio.analisis_clinico.auditlog.application.exception.AuditlogNotFoundException;
import com.laboratorio.analisis_clinico.auditlog.domain.exception.AuditlogDomainException;
import com.laboratorio.analisis_clinico.common.web.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

public class AuditlogExceptionHandler {
    @ExceptionHandler(AuditlogNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleNotFound(AuditlogNotFoundException ex) {
        return ErrorResponse.from(ex, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AuditlogDomainException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleDomain(AuditlogDomainException ex) {
        return ErrorResponse.from(ex, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleGeneral(Exception ex) {
        return ErrorResponse.internal();
    }
}
