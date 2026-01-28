package com.laboratorio.analisis_clinico.orden.application.usecase.commands;

import com.laboratorio.analisis_clinico.orden.application.exception.OrdenNotFoundException;
import com.laboratorio.analisis_clinico.orden.application.port.out.IOrdenRepo;
import com.laboratorio.analisis_clinico.orden.domain.Orden;
import com.laboratorio.analisis_clinico.personalMedico.application.exception.PersonalNotFoundException;
import com.laboratorio.analisis_clinico.personalMedico.application.port.out.IPersonalMedicoRepo;
import com.laboratorio.analisis_clinico.personalMedico.domain.PersonalMedico;

public class EditarOrden {

    private final IOrdenRepo ordenRepo;
    private final IPersonalMedicoRepo personalMedicoRepo;

    public EditarOrden(
            IOrdenRepo ordenRepo,
            IPersonalMedicoRepo personalMedicoRepo
    ) {
        this.ordenRepo = ordenRepo;
        this.personalMedicoRepo = personalMedicoRepo;
    }

    public void ejecutar(Long ordenId, Long nuevoDoctorId) {

        Orden orden = ordenRepo.findById(ordenId)
                .orElseThrow(() ->
                        new OrdenNotFoundException("La orden no existe.")
                );

        PersonalMedico medico = personalMedicoRepo.findById(nuevoDoctorId)
                .orElseThrow(() ->
                        new PersonalNotFoundException("El médico no existe.")
                );

        orden.cambiarPersonalMedico(medico);

        ordenRepo.save(orden);
    }
}

