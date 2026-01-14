package com.laboratorio.analisis_clinico.ordenAnalisis.application.usecase.Queries;

import com.laboratorio.analisis_clinico.ordenAnalisis.application.port.out.IOrdenAnalisisRepo;
import com.laboratorio.analisis_clinico.ordenAnalisis.domain.OrdenAnalisis;
import com.laboratorio.analisis_clinico.ordenAnalisis.domain.enume.Prioridad;

import java.util.List;

public class ListarAnalisisUrgentes {

    private final IOrdenAnalisisRepo ordenAnalisisRepo;

    public ListarAnalisisUrgentes(IOrdenAnalisisRepo ordenAnalisisRepo) {
        this.ordenAnalisisRepo = ordenAnalisisRepo;
    }

    public List<OrdenAnalisis> ejecutar() {
        return ordenAnalisisRepo.findByPrioridad(
                Prioridad.URGENTE
        );
    }
}

