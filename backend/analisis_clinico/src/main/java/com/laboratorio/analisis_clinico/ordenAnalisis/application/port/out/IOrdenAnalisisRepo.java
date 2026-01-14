package com.laboratorio.analisis_clinico.ordenAnalisis.application.port.out;

import com.laboratorio.analisis_clinico.ordenAnalisis.domain.OrdenAnalisis;
import com.laboratorio.analisis_clinico.ordenAnalisis.domain.enume.EstadoOrdenMedicaAnalisis;
import com.laboratorio.analisis_clinico.ordenAnalisis.domain.enume.Prioridad;

import java.util.List;
import java.util.Optional;

public interface IOrdenAnalisisRepo {

    Optional<OrdenAnalisis> findById(Long id);

    List<OrdenAnalisis> findByOrdenId(Long ordenId);

    List<OrdenAnalisis> findByEstado(EstadoOrdenMedicaAnalisis estado);

    List<OrdenAnalisis> findByPrioridad(Prioridad prioridad);

    void save(OrdenAnalisis ordenAnalisis);
}

