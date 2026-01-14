package com.laboratorio.analisis_clinico.infrastructure.persistence.jpa.paciente;

import com.laboratorio.analisis_clinico.paciente.domain.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PacienteJpaRepository
        extends JpaRepository<Paciente, Long> {

    Optional<Paciente> findByDni(String dni);

    List<Paciente> findByNombrePacienteContainingIgnoreCase(
            String nombre
    );
}

