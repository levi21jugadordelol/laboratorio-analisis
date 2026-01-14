package com.laboratorio.analisis_clinico.resultado.application.usecase.Queries;

import com.laboratorio.analisis_clinico.resultado.application.port.out.IResultadoRepo;
import com.laboratorio.analisis_clinico.resultado.domain.Resultado;

import java.time.LocalDateTime;
import java.util.List;

public class ListarResultadosPorFecha {

    private final IResultadoRepo resultadoRepo;

    public ListarResultadosPorFecha(IResultadoRepo resultadoRepo) {
        this.resultadoRepo = resultadoRepo;
    }

    public List<Resultado> ejecutar(
            LocalDateTime desde,
            LocalDateTime hasta
    ) {
        return resultadoRepo.findByFechaRegistroBetween(desde, hasta);
    }
}

