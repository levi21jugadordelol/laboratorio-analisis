package com.laboratorio.analisis_clinico.areaMedica.application.port.out;

import com.laboratorio.analisis_clinico.areaMedica.domain.AreaMedica;

import java.util.List;
import java.util.Optional;

public interface IAreaMedicaRepo {

    boolean existsById(Long areaMedicaId);

    Optional<AreaMedica> findById(Long areaMedicaId);

    void save(AreaMedica areaMedica);

    List<AreaMedica> findAll();
}

