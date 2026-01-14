package com.laboratorio.analisis_clinico.analisis.application.port.out;

import com.laboratorio.analisis_clinico.analisis.domain.Analisis;

import java.util.List;
import java.util.Optional;

public interface IAnalisisRepo {

    Optional<Analisis> findById(Long id);

    void save(Analisis analisis);

    List<Analisis> findActivos();

    List<Analisis> findInactivos();
    List<Analisis> findAll();

}

