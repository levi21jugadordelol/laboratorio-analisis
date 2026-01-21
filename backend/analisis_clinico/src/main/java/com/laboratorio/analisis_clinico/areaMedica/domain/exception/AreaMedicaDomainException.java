package com.laboratorio.analisis_clinico.areaMedica.domain.exception;

import com.laboratorio.analisis_clinico.areaMedica.domain.AreaMedica;

public class AreaMedicaDomainException extends RuntimeException{
    public AreaMedicaDomainException(String message) {
        super(message);
    }
}
