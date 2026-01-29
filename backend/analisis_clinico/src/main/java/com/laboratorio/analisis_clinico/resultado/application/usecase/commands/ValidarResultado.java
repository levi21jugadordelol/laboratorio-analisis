package com.laboratorio.analisis_clinico.resultado.application.usecase.commands;

import com.laboratorio.analisis_clinico.resultado.application.exception.ResultadoNotFoundException;
import com.laboratorio.analisis_clinico.resultado.application.port.out.IResultadoRepo;
import com.laboratorio.analisis_clinico.resultado.domain.Resultado;
import com.laboratorio.analisis_clinico.resultado.domain.service.ResultadoDomainService;

public class ValidarResultado {

    private final IResultadoRepo resultadoRepo;
    private final ResultadoDomainService domainService;

    public ValidarResultado(
            IResultadoRepo resultadoRepo,
            ResultadoDomainService domainService
    ) {
        this.resultadoRepo = resultadoRepo;
        this.domainService = domainService;
    }

    public void ejecutar(Long resultadoId) {

        Resultado resultado = resultadoRepo.findById(resultadoId)
                .orElseThrow(() -> new ResultadoNotFoundException("Resultado no encontrado"));

        domainService.validarResultado(resultado);

        resultadoRepo.save(resultado);
    }
}

