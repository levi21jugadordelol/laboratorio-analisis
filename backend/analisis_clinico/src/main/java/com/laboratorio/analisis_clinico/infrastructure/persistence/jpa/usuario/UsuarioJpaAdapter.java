package com.laboratorio.analisis_clinico.infrastructure.persistence.jpa.usuario;

import com.laboratorio.analisis_clinico.usuario.application.port.out.IUsuarioRepo;
import com.laboratorio.analisis_clinico.usuario.domain.Usuario;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UsuarioJpaAdapter implements IUsuarioRepo {

    private final UsuarioJpaRepository jpaRepository;

    public UsuarioJpaAdapter(
            UsuarioJpaRepository jpaRepository
    ) {
        this.jpaRepository = jpaRepository;
    }


    @Override
    public Optional<Usuario> findById(Long id) {
        return jpaRepository.findById(id);
    }

    @Override
    public Optional<Usuario> findByEmail(String email) {
        return jpaRepository.findByEmail(email);
    }

    @Override
    public List<Usuario> findAll() {
        return jpaRepository.findAll();
    }

    @Override
    public void save(Usuario usuario) {
        jpaRepository.save(usuario);
    }
}
