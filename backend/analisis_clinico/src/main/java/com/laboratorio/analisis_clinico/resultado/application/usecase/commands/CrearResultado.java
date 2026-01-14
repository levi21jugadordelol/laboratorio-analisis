package com.laboratorio.analisis_clinico.resultado.application.usecase.commands;

import com.laboratorio.analisis_clinico.ordenAnalisis.application.port.out.IOrdenAnalisisRepo;
import com.laboratorio.analisis_clinico.ordenAnalisis.domain.OrdenAnalisis;
import com.laboratorio.analisis_clinico.resultado.application.port.out.IResultadoRepo;
import com.laboratorio.analisis_clinico.resultado.domain.Resultado;
import com.laboratorio.analisis_clinico.resultado.domain.service.ResultadoDomainService;

import java.util.Map;

public class CrearResultado {

    private final IOrdenAnalisisRepo ordenAnalisisRepo;
    private final IResultadoRepo resultadoRepo;
    private final ResultadoDomainService domainService;

    public CrearResultado(
            IOrdenAnalisisRepo ordenAnalisisRepo,
            IResultadoRepo resultadoRepo,
            ResultadoDomainService domainService
    ) {
        this.ordenAnalisisRepo = ordenAnalisisRepo;
        this.resultadoRepo = resultadoRepo;
        this.domainService = domainService;
    }

    public void ejecutar(
            Long ordenAnalisisId,
            Map<String, Object> resultadoJson,
            String observacion,
            Long usuarioId
    ) {

        OrdenAnalisis ordenAnalisis = ordenAnalisisRepo.findById(ordenAnalisisId)
                .orElseThrow(() -> new IllegalArgumentException("Análisis no encontrado"));

        Resultado resultado = domainService.registrarResultadoInicial(
                ordenAnalisis,
                resultadoJson,
                observacion,
                usuarioId
        );

        resultadoRepo.save(resultado);
    }
}

