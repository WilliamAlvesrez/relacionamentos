package com.william.mongodb.relacionamentos.relacionamentos.controller;

import com.william.mongodb.relacionamentos.relacionamentos.models.Estudante;
import com.william.mongodb.relacionamentos.relacionamentos.repository.EstudanteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estudantes")
public class EstudanteController {

    @Autowired
    private EstudanteRepository estudanteRepository;

    // POST /estudantes - Adiciona um estudante
    @PostMapping
    public Estudante create(@RequestBody Estudante estudante) {
        return estudanteRepository.save(estudante);
    }

    // GET /estudantes - Retorna todos os estudantes
    @GetMapping
    public List<Estudante> getAll() {
        return estudanteRepository.findAll();
    }

    // GET /estudantes/{id} - Retorna um estudante por ID
    @GetMapping("/{id}")
    public Estudante getById(@PathVariable String id) {
        return estudanteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estudante não encontrado com o ID: " + id));
    }

    // PUT /estudantes/{id} - Atualiza um estudante
    @PutMapping("/{id}")
    public Estudante update(@PathVariable String id, @RequestBody Estudante estudanteAtualizado) {
        Estudante estudanteExistente = estudanteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estudante não encontrado com o ID: " + id));

        estudanteExistente.setNome(estudanteAtualizado.getNome());
        estudanteExistente.setCursos(estudanteAtualizado.getCursos());
        return estudanteRepository.save(estudanteExistente);
    }

    // DELETE /estudantes/{id} - Deleta um estudante
    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        Estudante estudante = estudanteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estudante não encontrado com o ID: " + id));
        estudanteRepository.delete(estudante);
    }
}
