package com.william.mongodb.relacionamentos.relacionamentos.controller;

import com.william.mongodb.relacionamentos.relacionamentos.models.Curso;
import com.william.mongodb.relacionamentos.relacionamentos.repository.CursoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cursos")
public class CursoController {

    @Autowired
    private CursoRepository cursoRepository;

    // POST /cursos - Adiciona um curso
    @PostMapping
    public Curso create(@RequestBody Curso curso) {
        return cursoRepository.save(curso);
    }

    // GET /cursos - Retorna todos os cursos
    @GetMapping
    public List<Curso> getAll() {
        return cursoRepository.findAll();
    }

    // GET /cursos/{id} - Retorna um curso por ID
    @GetMapping("/{id}")
    public Curso getById(@PathVariable String id) {
        return cursoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Curso não encontrado com o ID: " + id));
    }

    // PUT /cursos/{id} - Atualiza um curso
    @PutMapping("/{id}")
    public Curso update(@PathVariable String id, @RequestBody Curso cursoAtualizado) {
        Curso cursoExistente = cursoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Curso não encontrado com o ID: " + id));

        cursoExistente.setNome(cursoAtualizado.getNome());
        cursoExistente.setEstudantes(cursoAtualizado.getEstudantes());
        return cursoRepository.save(cursoExistente);
    }

    // DELETE /cursos/{id} - Deleta um curso
    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        Curso curso = cursoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Curso não encontrado com o ID: " + id));
        cursoRepository.delete(curso);
    }
}
