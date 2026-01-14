package com.laboratorio.analisis_clinico.infrastructure.persistence.jpa.formatoAnalisis;

import com.laboratorio.analisis_clinico.formatoAnalisis.domain.FormatoAnalisis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FormatoAnalisisJpaRepository
        extends JpaRepository<FormatoAnalisis, Long> {

    List<FormatoAnalisis> findByAnalisisId(Long analisisId);

    Optional<FormatoAnalisis> findByAnalisisIdAndNombreFormato(
            Long analisisId,
            String nombreFormato
    );

    List<FormatoAnalisis> findByAnalisisIdAndVersion(
            Long analisisId,
            double version
    );

    List<FormatoAnalisis>
    findByAnalisisIdAndNombreFormatoOrderByVersionDesc(
            Long analisisId,
            String nombreFormato
    );
}

