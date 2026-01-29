package com.laboratorio.analisis_clinico.resultado.application.usecase.commands;

import com.laboratorio.analisis_clinico.resultado.application.exception.ResultadoNotFoundException;
import com.laboratorio.analisis_clinico.resultado.application.port.out.IResultadoRepo;
import com.laboratorio.analisis_clinico.resultado.domain.Resultado;
import com.laboratorio.analisis_clinico.resultado.domain.service.ResultadoDomainService;

import java.util.Map;
public class VersionarResultadosModificados {

    private final IResultadoRepo resultadoRepo;
    private final ResultadoDomainService domainService;

    public VersionarResultadosModificados(
            IResultadoRepo resultadoRepo,
            ResultadoDomainService domainService
    ) {
        this.resultadoRepo = resultadoRepo;
        this.domainService = domainService;
    }

    public void ejecutar(
            Long resultadoId,
            Map<String, Object> nuevoResultado,
            String motivo,
            Long usuarioId
    ) {

        Resultado resultadoActual = resultadoRepo.findById(resultadoId)
                .orElseThrow(() -> new ResultadoNotFoundException("Resultado no encontrado"));

        Resultado nuevaVersion = domainService.corregirResultadoCreandoNuevaVersion(
                resultadoActual,
                nuevoResultado,
                motivo,
                usuarioId
        );

        resultadoRepo.save(nuevaVersion);
    }
}

