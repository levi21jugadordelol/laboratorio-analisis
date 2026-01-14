package com.laboratorio.analisis_clinico.formatoAnalisis.application.port.out;

import com.laboratorio.analisis_clinico.formatoAnalisis.domain.FormatoAnalisis;

import java.util.List;
import java.util.Optional;

public interface IFormatoAnalisisRepo {

    Optional<FormatoAnalisis> findById(Long id);

    List<FormatoAnalisis> findByAnalisisId(Long analisisId);

    void save(FormatoAnalisis formatoAnalisis);
    Optional<FormatoAnalisis> findByAnalisisIdAndNombreFormato(
            Long analisisId,
            String nombreFormato
    );

    List<FormatoAnalisis> findByAnalisisIdAndVersion(
            Long analisisId,
            double version
    );

    List<FormatoAnalisis> findByAnalisisIdAndNombreFormatoOrderByVersionDesc(
            Long analisisId,
            String nombreFormato
    );



}

