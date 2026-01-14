package com.laboratorio.analisis_clinico.infrastructure.persistence.jpa.resultado;

import com.laboratorio.analisis_clinico.resultado.application.port.out.IResultadoRepo;
import com.laboratorio.analisis_clinico.resultado.domain.Resultado;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public class ResultadoJpaAdapter implements IResultadoRepo {
    private final ResultadoJpaRepository jpaRepository;

    public ResultadoJpaAdapter(
            ResultadoJpaRepository jpaRepository
    ) {
        this.jpaRepository = jpaRepository;
    }
    @Override
    public Optional<Resultado> findById(Long id) {
        return jpaRepository.findById(id);
    }

    @Override
    public Optional<Resultado> findByOrdenAnalisisId(Long ordenAnalisisId) {
        return jpaRepository.findByOrdenAnalisisId(ordenAnalisisId);
    }

    @Override
    public void save(Resultado resultado) {
        jpaRepository.save(resultado);
    }

    @Override
    public List<Resultado> findByFechaRegistroBetween(LocalDateTime inicio, LocalDateTime fin) {
        return jpaRepository.findByFechaRegistroBetween(
                inicio,
                fin
        );
    }
}
