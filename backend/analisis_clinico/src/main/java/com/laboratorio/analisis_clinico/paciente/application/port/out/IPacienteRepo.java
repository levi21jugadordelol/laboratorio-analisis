package com.laboratorio.analisis_clinico.paciente.application.port.out;

import com.laboratorio.analisis_clinico.paciente.domain.Paciente;

import java.util.List;
import java.util.Optional;

public interface IPacienteRepo {

    Optional<Paciente> findById(Long id);

    Optional<Paciente> findByDni(String dni);

    void save(Paciente paciente);

    List<Paciente> findByNombreContaining(String nombre);

}

