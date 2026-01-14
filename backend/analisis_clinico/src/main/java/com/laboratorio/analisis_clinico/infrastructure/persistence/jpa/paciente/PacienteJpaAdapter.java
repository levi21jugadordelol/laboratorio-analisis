package com.laboratorio.analisis_clinico.infrastructure.persistence.jpa.paciente;

import com.laboratorio.analisis_clinico.paciente.application.port.out.IPacienteRepo;
import com.laboratorio.analisis_clinico.paciente.domain.Paciente;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PacienteJpaAdapter implements IPacienteRepo {

    private final PacienteJpaRepository jpaRepository;

    public PacienteJpaAdapter(
            PacienteJpaRepository jpaRepository
    ) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Optional<Paciente> findById(Long id) {
        return jpaRepository.findById(id);
    }

    @Override
    public Optional<Paciente> findByDni(String dni) {
        return jpaRepository.findByDni(dni);
    }

    @Override
    public void save(Paciente paciente) {
        jpaRepository.save(paciente);
    }

    @Override
    public List<Paciente> findByNombreContaining(String nombre) {
        return jpaRepository
                .findByNombrePacienteContainingIgnoreCase(nombre);
    }
}

