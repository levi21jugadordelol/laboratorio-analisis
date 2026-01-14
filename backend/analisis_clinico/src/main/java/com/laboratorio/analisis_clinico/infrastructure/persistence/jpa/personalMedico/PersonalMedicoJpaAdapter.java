package com.laboratorio.analisis_clinico.infrastructure.persistence.jpa.personalMedico;

import com.laboratorio.analisis_clinico.personalMedico.application.port.out.IPersonalMedicoRepo;
import com.laboratorio.analisis_clinico.personalMedico.domain.PersonalMedico;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public class PersonalMedicoJpaAdapter implements IPersonalMedicoRepo {

    private final PersonalMedicoJpaRepository jpaRepository;

    public PersonalMedicoJpaAdapter(
            PersonalMedicoJpaRepository jpaRepository
    ) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Optional<PersonalMedico> findById(Long id) {
        return jpaRepository.findById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return jpaRepository.existsById(id);
    }

    @Override
    public void save(PersonalMedico personalMedico) {
        jpaRepository.save(personalMedico);
    }
}
