package com.laboratorio.analisis_clinico.infrastructure.persistence.jpa.ordenAnalisis;

import com.laboratorio.analisis_clinico.ordenAnalisis.domain.OrdenAnalisis;
import com.laboratorio.analisis_clinico.ordenAnalisis.domain.enume.EstadoOrdenMedicaAnalisis;
import com.laboratorio.analisis_clinico.ordenAnalisis.domain.enume.Prioridad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdenAnalisisJpaRepository
        extends JpaRepository<OrdenAnalisis, Long> {

    List<OrdenAnalisis> findByOrdenId(Long ordenId);

    List<OrdenAnalisis> findByEstado(
            EstadoOrdenMedicaAnalisis estado
    );

    List<OrdenAnalisis> findByPrioridad(Prioridad prioridad);
}

