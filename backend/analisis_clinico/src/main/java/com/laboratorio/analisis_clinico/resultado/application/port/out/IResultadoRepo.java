package com.laboratorio.analisis_clinico.resultado.application.port.out;

import com.laboratorio.analisis_clinico.resultado.domain.Resultado;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface IResultadoRepo {

    Optional<Resultado> findById(Long id);

    Optional<Resultado> findByOrdenAnalisisId(Long ordenAnalisisId);

    void save(Resultado resultado);


    List<Resultado> findByFechaRegistroBetween(
            LocalDateTime inicio,
            LocalDateTime fin
    );

}

