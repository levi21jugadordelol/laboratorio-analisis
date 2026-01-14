package com.laboratorio.analisis_clinico.infrastructure.persistence.jpa.analisis;

import com.laboratorio.analisis_clinico.analisis.domain.Analisis;
import com.laboratorio.analisis_clinico.analisis.domain.enume.EstadoAnalisis;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnalisisJpaRepository
        extends JpaRepository<Analisis, Long> {

    List<Analisis> findByEstadoAnalisis(EstadoAnalisis estadoAnalisis);
}
