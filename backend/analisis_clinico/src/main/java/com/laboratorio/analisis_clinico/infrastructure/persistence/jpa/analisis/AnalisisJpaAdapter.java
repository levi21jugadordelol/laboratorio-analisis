package com.laboratorio.analisis_clinico.infrastructure.persistence.jpa.analisis;

import com.laboratorio.analisis_clinico.analisis.application.port.out.IAnalisisRepo;
import com.laboratorio.analisis_clinico.analisis.domain.Analisis;
import com.laboratorio.analisis_clinico.analisis.domain.enume.EstadoAnalisis;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class AnalisisJpaAdapter implements IAnalisisRepo {
    private final AnalisisJpaRepository jpaRepository;

    public AnalisisJpaAdapter(AnalisisJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }
    @Override
    public Optional<Analisis> findById(Long id) {
        return jpaRepository.findById(id);
    }

    @Override
    public void save(Analisis analisis) {
        jpaRepository.save(analisis);
    }

    @Override
    public List<Analisis> findActivos() {
        return jpaRepository.findByEstadoAnalisis(EstadoAnalisis.ACTIVO);
    }

    @Override
    public List<Analisis> findInactivos() {
        return jpaRepository.findByEstadoAnalisis(EstadoAnalisis.INACTIVO);
    }

    @Override
    public List<Analisis> findAll() {
        return jpaRepository.findAll();
    }
}
