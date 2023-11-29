package com.desafio.user.rest;

import com.desafio.user.model.Usuario;
import com.desafio.user.service.UsuarioService;

import java.util.List;

import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsuarioResource {
    @Autowired
    private UsuarioService service;

    @GetMapping("api/usuario")
    public List<Usuario> getAllUsuarios(){
        return service.findAll();
    }

    @GetMapping("/api/usuario/{id}")
    public ResponseEntity<Usuario> getUsuario(@PathVariable("id") Long id){
        final Usuario usuario = service.findById(id);
        return ResponseEntity.ok(usuario);
    }

    @PostMapping("api/usuario")
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario createUsuario(@Valid @RequestBody Usuario usuario){
        return service.save(usuario);
    }


    @PutMapping("/api/usuario/{id}")
    public ResponseEntity<Usuario> updateUsuario(@PathVariable("id") Long id, @Valid @RequestBody Usuario dto){
        final Usuario usuario = service.update(id, dto);
        return ResponseEntity.ok(usuario);
    }

    @DeleteMapping("/api/usuario/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) {
        service.delete(id);
    }
}

