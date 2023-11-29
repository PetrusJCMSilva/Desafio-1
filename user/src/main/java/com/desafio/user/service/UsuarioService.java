package com.desafio.user.service;


import java.util.List;
import java.util.Optional;

import com.desafio.user.model.Usuario;
import com.desafio.user.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;


public class UsuarioService {
    @Autowired
    private UsuarioRepository repository;
    public List<Usuario> findAll(){
        return repository.findAll();
    }

    public Usuario findById(final long id){
        final Optional<Usuario> usuario = repository.findById(id);
        return usuario.get();
    }

    public Usuario save(final Usuario usuario){
        return repository.save(usuario);
    }

    public Usuario update(final Long id, final Usuario dto){
        final Optional <Usuario> usuarioEntity = repository.findById(id);
        final Usuario usuario = usuarioEntity.get();

        usuario.setNome(dto.getNome());
        usuario.setSenha(dto.getSenha());
        return repository.save(usuario);
    }

    public void delete(final Long id){
        final Usuario usuario = findById(id);
        repository.delete(usuario);
    }

}
