package com.laboratorio.analisis_clinico.orden.application.usecase.commands;

import com.laboratorio.analisis_clinico.orden.application.port.out.IOrdenRepo;
import com.laboratorio.analisis_clinico.orden.domain.Orden;
import com.laboratorio.analisis_clinico.orden.domain.enume.TipoOrden;

public class CrearOrden {

    private final IOrdenRepo ordenRepo;

    public CrearOrden(IOrdenRepo ordenRepo) {
        this.ordenRepo = ordenRepo;
    }

    public void ejecutar(
            Long pacienteId,
            TipoOrden tipoOrden,
            Long doctorId,
            Long usuarioId
    ) {

        Orden orden = new Orden(
                pacienteId,
                tipoOrden,
                doctorId,
                usuarioId
        );

        ordenRepo.save(orden);
    }
}

