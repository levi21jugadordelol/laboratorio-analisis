package com.laboratorio.analisis_clinico.infrastructure.persistence.jpa.ordenAnalisis;


import com.laboratorio.analisis_clinico.ordenAnalisis.application.port.out.IOrdenAnalisisRepo;
import com.laboratorio.analisis_clinico.ordenAnalisis.domain.OrdenAnalisis;
import com.laboratorio.analisis_clinico.ordenAnalisis.domain.enume.EstadoOrdenMedicaAnalisis;
import com.laboratorio.analisis_clinico.ordenAnalisis.domain.enume.Prioridad;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class OrdenAnalisisJpaAdapter implements IOrdenAnalisisRepo {

    private final OrdenAnalisisJpaRepository jpaRepository;

    public OrdenAnalisisJpaAdapter(
            OrdenAnalisisJpaRepository jpaRepository
    ) {
        this.jpaRepository = jpaRepository;
    }


    @Override
    public Optional<OrdenAnalisis> findById(Long id) {
        return jpaRepository.findById(id);
    }

    @Override
    public List<OrdenAnalisis> findByOrdenId(Long ordenId) {
        return jpaRepository.findByOrdenId(ordenId);
    }

    @Override
    public List<OrdenAnalisis> findByEstado(EstadoOrdenMedicaAnalisis estado) {
        return jpaRepository.findByEstado(estado);
    }

    @Override
    public List<OrdenAnalisis> findByPrioridad(Prioridad prioridad) {
        return jpaRepository.findByPrioridad(prioridad);
    }

    @Override
    public void save(OrdenAnalisis ordenAnalisis) {
        jpaRepository.save(ordenAnalisis);
    }
}
