package com.laboratorio.analisis_clinico.infrastructure.persistence.jpa.personalMedico;

import com.laboratorio.analisis_clinico.personalMedico.domain.PersonalMedico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonalMedicoJpaRepository
        extends JpaRepository<PersonalMedico, Long> {
}

