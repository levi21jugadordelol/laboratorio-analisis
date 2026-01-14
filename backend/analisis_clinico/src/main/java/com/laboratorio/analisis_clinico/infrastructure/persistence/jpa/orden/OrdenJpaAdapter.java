package com.laboratorio.analisis_clinico.infrastructure.persistence.jpa.orden;

import com.laboratorio.analisis_clinico.orden.application.port.out.IOrdenRepo;
import com.laboratorio.analisis_clinico.orden.domain.Orden;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public class OrdenJpaAdapter implements IOrdenRepo {
    private final OrdenJpaRepository jpaRepository;

    public OrdenJpaAdapter(OrdenJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }
    @Override
    public Optional<Orden> findById(Long id) {
        return jpaRepository.findById(id);
    }

    @Override
    public List<Orden> findByPacienteId(Long pacienteId) {
        return jpaRepository.findByPacienteId(pacienteId);
    }

    @Override
    public List<Orden> findByFechaCreacionBetween(LocalDateTime inicio, LocalDateTime fin) {
        return jpaRepository.findByFechaCreacionBetween(inicio, fin);
    }

    @Override
    public void save(Orden orden) {
        jpaRepository.save(orden);
    }

    @Override
    public List<Orden> findByDoctorId(Long doctorId) {
        return jpaRepository.findByDoctorId(doctorId);
    }
}
