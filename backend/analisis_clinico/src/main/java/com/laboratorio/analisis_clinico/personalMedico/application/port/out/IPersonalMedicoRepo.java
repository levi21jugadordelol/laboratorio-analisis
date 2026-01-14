package com.laboratorio.analisis_clinico.personalMedico.application.port.out;

import com.laboratorio.analisis_clinico.areaMedica.domain.AreaMedica;
import com.laboratorio.analisis_clinico.personalMedico.domain.PersonalMedico;

import java.util.Optional;

public interface IPersonalMedicoRepo {

    Optional<PersonalMedico> findById(Long id);

    boolean existsById(Long id);

    void save(PersonalMedico personalMedico);
}


