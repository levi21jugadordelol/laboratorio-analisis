package com.laboratorio.analisis_clinico.infrastructure.persistence.jpa.areamedica;

import com.laboratorio.analisis_clinico.areaMedica.application.port.out.IAreaMedicaRepo;
import com.laboratorio.analisis_clinico.areaMedica.domain.AreaMedica;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class AreaMedicaJpaAdapter implements IAreaMedicaRepo {

    private final AreaMedicaJpaRepository jpaRepository;

    public AreaMedicaJpaAdapter(AreaMedicaJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public boolean existsById(Long areaMedicaId) {
        return jpaRepository.existsById(areaMedicaId);
    }

    @Override
    public Optional<AreaMedica> findById(Long areaMedicaId) {
        return jpaRepository.findById(areaMedicaId);
    }

    @Override
    public void save(AreaMedica areaMedica) {
        jpaRepository.save(areaMedica);
    }

    @Override
    public List<AreaMedica> findAll() {
        return jpaRepository.findAll();
    }
}
