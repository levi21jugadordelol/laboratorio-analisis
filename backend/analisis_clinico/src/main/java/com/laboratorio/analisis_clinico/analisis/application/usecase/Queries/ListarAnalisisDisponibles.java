package com.laboratorio.analisis_clinico.analisis.application.usecase.Queries;

import com.laboratorio.analisis_clinico.analisis.application.port.out.IAnalisisRepo;
import com.laboratorio.analisis_clinico.analisis.domain.Analisis;

import java.util.List;

public class ListarAnalisisDisponibles {

    private final IAnalisisRepo analisisRepo;

    public ListarAnalisisDisponibles(IAnalisisRepo analisisRepo) {
        this.analisisRepo = analisisRepo;
    }

    public List<Analisis> ejecutar() {

        return analisisRepo.findAll().stream()
                .filter(Analisis::estaActivo)
                .toList();
    }
}

