package com.laboratorio.analisis_clinico.infrastructure.persistence.jpa.areamedica;

import com.laboratorio.analisis_clinico.areaMedica.domain.AreaMedica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AreaMedicaJpaRepository
        extends JpaRepository<AreaMedica, Long> {
}

