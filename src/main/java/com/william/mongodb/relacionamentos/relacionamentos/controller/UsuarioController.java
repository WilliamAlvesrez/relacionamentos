package com.william.mongodb.relacionamentos.relacionamentos.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.william.mongodb.relacionamentos.relacionamentos.models.Perfil;
import com.william.mongodb.relacionamentos.relacionamentos.models.Usuario;
import com.william.mongodb.relacionamentos.relacionamentos.repository.PerfilRepository;
import com.william.mongodb.relacionamentos.relacionamentos.repository.UsuarioRepository;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PerfilRepository perfilRepository;

    @GetMapping
    public List<Usuario> getAll() {
        return usuarioRepository.findAll();
    }

    @GetMapping("/{id}")
    public Usuario getById(@PathVariable String id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com o ID: " + id));
    }

    @PostMapping
    public Usuario create(@RequestBody Usuario usuario) {
        if (usuario.getPerfil() != null && usuario.getPerfil().getId() == null) {
            Perfil perfilSalvo = perfilRepository.save(usuario.getPerfil());
            usuario.setPerfil(perfilSalvo);
        }
        return usuarioRepository.save(usuario);
    }

    @PutMapping("/{id}")
    public Usuario update(@PathVariable String id, @RequestBody Usuario usuarioAtualizado) {
        Usuario usuarioExistente = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com o ID: " + id));

        usuarioExistente.setNome(usuarioAtualizado.getNome());
        if (usuarioAtualizado.getPerfil() != null && usuarioAtualizado.getPerfil().getId() == null) {
            Perfil perfilSalvo = perfilRepository.save(usuarioAtualizado.getPerfil());
            usuarioExistente.setPerfil(perfilSalvo);
        } else {
            usuarioExistente.setPerfil(usuarioAtualizado.getPerfil());
        }

        usuarioExistente.setPostagens(usuarioAtualizado.getPostagens());
        return usuarioRepository.save(usuarioExistente);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com o ID: " + id));
        usuarioRepository.delete(usuario);
    }
}