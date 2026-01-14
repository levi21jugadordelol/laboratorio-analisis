package com.laboratorio.analisis_clinico.infrastructure.persistence.jpa.formatoAnalisis;

import com.laboratorio.analisis_clinico.formatoAnalisis.application.port.out.IFormatoAnalisisRepo;
import com.laboratorio.analisis_clinico.formatoAnalisis.domain.FormatoAnalisis;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class FormatoAnalisisJpaAdapter implements IFormatoAnalisisRepo {

    private final FormatoAnalisisJpaRepository jpaRepository;

    public FormatoAnalisisJpaAdapter(
            FormatoAnalisisJpaRepository jpaRepository
    ) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Optional<FormatoAnalisis> findById(Long id) {
        return jpaRepository.findById(id);
    }

    @Override
    public List<FormatoAnalisis> findByAnalisisId(Long analisisId) {
        return jpaRepository.findByAnalisisId(analisisId);
    }

    @Override
    public void save(FormatoAnalisis formatoAnalisis) {
        jpaRepository.save(formatoAnalisis);
    }

    @Override
    public Optional<FormatoAnalisis> findByAnalisisIdAndNombreFormato(Long analisisId, String nombreFormato) {
        return jpaRepository
                .findByAnalisisIdAndNombreFormato(
                        analisisId,
                        nombreFormato
                );
    }

    @Override
    public List<FormatoAnalisis> findByAnalisisIdAndVersion(Long analisisId, double version) {
        return jpaRepository
                .findByAnalisisIdAndVersion(
                        analisisId,
                        version
                );
    }

    @Override
    public List<FormatoAnalisis> findByAnalisisIdAndNombreFormatoOrderByVersionDesc(Long analisisId, String nombreFormato) {
        return jpaRepository
                .findByAnalisisIdAndNombreFormatoOrderByVersionDesc(
                        analisisId,
                        nombreFormato
                );
    }
}
