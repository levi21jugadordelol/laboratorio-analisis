package com.laboratorio.analisis_clinico.infrastructure.persistence.jpa.reporte;

import com.laboratorio.analisis_clinico.reporte.application.port.out.IReporteRepo;
import com.laboratorio.analisis_clinico.reporte.domain.Reporte;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ReporteJpaAdapter  implements IReporteRepo {

    private final ReporteJpaRepository jpaRepository;

    public ReporteJpaAdapter(
            ReporteJpaRepository jpaRepository
    ) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Optional<Reporte> findById(Long id) {
        return jpaRepository.findById(id);
    }

    @Override
    public void save(Reporte reporte) {
        jpaRepository.save(reporte);
    }

    @Override
    public List<Reporte> findAll() {
        return jpaRepository.findAll();
    }
}
