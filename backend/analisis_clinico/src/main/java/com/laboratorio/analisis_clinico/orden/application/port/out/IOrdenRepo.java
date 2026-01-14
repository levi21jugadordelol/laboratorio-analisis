package com.laboratorio.analisis_clinico.orden.application.port.out;

import com.laboratorio.analisis_clinico.orden.domain.Orden;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface IOrdenRepo {

    Optional<Orden> findById(Long id);

    List<Orden> findByPacienteId(Long pacienteId);

    List<Orden> findByFechaCreacionBetween(
            LocalDateTime inicio,
            LocalDateTime fin
    );

    void save(Orden orden);

    List<Orden> findByDoctorId(Long doctorId);

}


