package com.laboratorio.analisis_clinico.infrastructure.persistence.jpa.reporte;

import com.laboratorio.analisis_clinico.reporte.domain.Reporte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReporteJpaRepository
        extends JpaRepository<Reporte, Long> {
}

