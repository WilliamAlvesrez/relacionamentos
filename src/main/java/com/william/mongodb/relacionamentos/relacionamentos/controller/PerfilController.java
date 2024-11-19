package com.william.mongodb.relacionamentos.relacionamentos.controller;

import com.william.mongodb.relacionamentos.relacionamentos.models.Perfil;
import com.william.mongodb.relacionamentos.relacionamentos.repository.PerfilRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/perfis")
public class PerfilController {

    @Autowired
    private PerfilRepository perfilRepository;

    // POST /perfis - Adiciona um perfil
    @PostMapping
    public Perfil create(@RequestBody Perfil perfil) {
        return perfilRepository.save(perfil);
    }

    // GET /perfis - Retorna todos os perfis
    @GetMapping
    public List<Perfil> getAll() {
        return perfilRepository.findAll();
    }

    // Retorna um perfil por ID
    @GetMapping("/{id}")
    public Perfil getById(@PathVariable String id) {
        return perfilRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Perfil não encontrado com o ID: " + id));
    }

    // Atualiza um perfil
    @PutMapping("/{id}")
    public Perfil update(@PathVariable String id, @RequestBody Perfil perfilAtualizado) {
        Perfil perfilExistente = perfilRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Perfil não encontrado com o ID: " + id));

        perfilExistente.setBio(perfilAtualizado.getBio());
        perfilExistente.setAvatarUrl(perfilAtualizado.getAvatarUrl());
        return perfilRepository.save(perfilExistente);
    }

    //  Deleta um perfil
    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        Perfil perfil = perfilRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Perfil não encontrado com o ID: " + id));
        perfilRepository.delete(perfil);
    }
}
