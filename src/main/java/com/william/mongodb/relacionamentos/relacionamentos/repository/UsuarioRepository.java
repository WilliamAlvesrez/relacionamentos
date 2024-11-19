package com.william.mongodb.relacionamentos.relacionamentos.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.william.mongodb.relacionamentos.relacionamentos.models.Usuario;

public interface UsuarioRepository extends MongoRepository<Usuario, String> {
    
}
