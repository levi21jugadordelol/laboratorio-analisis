package com.laboratorio.analisis_clinico.paciente.adapter.in.web.exception;
//package com.laboratorio.Paciente_clinico.paciente.adapter.in.web.exception;

import com.laboratorio.analisis_clinico.common.web.ErrorResponse;
import com.laboratorio.analisis_clinico.paciente.application.exception.PacienteNotFoundException;
import com.laboratorio.analisis_clinico.paciente.domain.exception.PacienteDomainException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

public class PacienteExceptionHandler {
    @ExceptionHandler(PacienteNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleNotFound(PacienteNotFoundException ex) {
        return ErrorResponse.from(ex, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PacienteDomainException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleDomain(PacienteDomainException ex) {
        return ErrorResponse.from(ex, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleGeneral(Exception ex) {
        return ErrorResponse.internal();
    }
}
