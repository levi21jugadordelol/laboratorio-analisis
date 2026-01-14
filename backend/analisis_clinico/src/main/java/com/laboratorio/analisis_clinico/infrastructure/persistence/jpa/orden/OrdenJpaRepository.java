package com.laboratorio.analisis_clinico.infrastructure.persistence.jpa.orden;

import com.laboratorio.analisis_clinico.orden.domain.Orden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OrdenJpaRepository
        extends JpaRepository<Orden, Long> {

    List<Orden> findByPacienteId(Long pacienteId);

    List<Orden> findByDoctorId(Long doctorId);

    List<Orden> findByFechaCreacionBetween(
            LocalDateTime inicio,
            LocalDateTime fin
    );
}
