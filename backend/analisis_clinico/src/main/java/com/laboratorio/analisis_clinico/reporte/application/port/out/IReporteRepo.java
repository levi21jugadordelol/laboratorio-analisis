package com.laboratorio.analisis_clinico.reporte.application.port.out;

import com.laboratorio.analisis_clinico.reporte.domain.Reporte;

import java.util.List;
import java.util.Optional;

public interface IReporteRepo {

    Optional<Reporte> findById(Long id);

    void save(Reporte reporte);

    List<Reporte> findAll();

}

