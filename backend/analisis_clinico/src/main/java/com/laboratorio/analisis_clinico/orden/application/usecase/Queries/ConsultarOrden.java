package com.laboratorio.analisis_clinico.orden.application.usecase.Queries;

import com.laboratorio.analisis_clinico.orden.application.exception.OrdenNotFoundException;
import com.laboratorio.analisis_clinico.orden.application.port.out.IOrdenRepo;
import com.laboratorio.analisis_clinico.orden.domain.Orden;

public class ConsultarOrden {

    private final IOrdenRepo ordenRepo;

    public ConsultarOrden(IOrdenRepo ordenRepo) {
        this.ordenRepo = ordenRepo;
    }

    public Orden ejecutar(Long ordenId) {
        return ordenRepo.findById(ordenId)
                .orElseThrow(() ->
                        new OrdenNotFoundException(
                                "La orden no existe."
                        )
                );
    }
}

