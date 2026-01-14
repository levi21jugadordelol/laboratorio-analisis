package com.laboratorio.analisis_clinico.ordenAnalisis.application.usecase.Queries;

import com.laboratorio.analisis_clinico.ordenAnalisis.application.port.out.IOrdenAnalisisRepo;
import com.laboratorio.analisis_clinico.ordenAnalisis.domain.OrdenAnalisis;
import com.laboratorio.analisis_clinico.ordenAnalisis.domain.enume.EstadoOrdenMedicaAnalisis;

import java.util.List;

public class ListarAnalisisPendientes {

    private final IOrdenAnalisisRepo ordenAnalisisRepo;

    public ListarAnalisisPendientes(IOrdenAnalisisRepo ordenAnalisisRepo) {
        this.ordenAnalisisRepo = ordenAnalisisRepo;
    }

    public List<OrdenAnalisis> ejecutar() {
        return ordenAnalisisRepo.findByEstado(
                EstadoOrdenMedicaAnalisis.PENDIENTE
        );
    }
}

