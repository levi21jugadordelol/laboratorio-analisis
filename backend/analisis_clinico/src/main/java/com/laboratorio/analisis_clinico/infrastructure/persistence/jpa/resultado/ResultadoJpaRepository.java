package com.laboratorio.analisis_clinico.infrastructure.persistence.jpa.resultado;

import com.laboratorio.analisis_clinico.resultado.domain.Resultado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ResultadoJpaRepository
        extends JpaRepository<Resultado, Long> {

    Optional<Resultado> findByOrdenAnalisisId(Long ordenAnalisisId);

    List<Resultado> findByFechaRegistroBetween(
            LocalDateTime inicio,
            LocalDateTime fin
    );
}

