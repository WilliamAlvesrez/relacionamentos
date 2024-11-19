package com.william.mongodb.relacionamentos.relacionamentos.controller;

import com.william.mongodb.relacionamentos.relacionamentos.models.Postagem;
import com.william.mongodb.relacionamentos.relacionamentos.repository.PostagemRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/postagens")
public class PostagemController {

    @Autowired
    private PostagemRepository postagemRepository;

    // POST /postagens - Adiciona uma postagem
    @PostMapping
    public Postagem create(@RequestBody Postagem postagem) {
        return postagemRepository.save(postagem);
    }

    // GET /postagens - Retorna todas as postagens
    @GetMapping
    public List<Postagem> getAll() {
        return postagemRepository.findAll();
    }

    // GET /postagens/{id} - Retorna uma postagem por ID
    @GetMapping("/{id}")
    public Postagem getById(@PathVariable String id) {
        return postagemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Postagem não encontrada com o ID: " + id));
    }

    // PUT /postagens/{id} - Atualiza uma postagem
    @PutMapping("/{id}")
    public Postagem update(@PathVariable String id, @RequestBody Postagem postagemAtualizada) {
        Postagem postagemExistente = postagemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Postagem não encontrada com o ID: " + id));

        postagemExistente.setTitulo(postagemAtualizada.getTitulo());
        postagemExistente.setConteudo(postagemAtualizada.getConteudo());
        return postagemRepository.save(postagemExistente);
    }

    // DELETE /postagens/{id} - Deleta uma postagem
    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        Postagem postagem = postagemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Postagem não encontrada com o ID: " + id));
        postagemRepository.delete(postagem);
    }
}
