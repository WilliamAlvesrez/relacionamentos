package com.william.mongodb.relacionamentos.relacionamentos.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.william.mongodb.relacionamentos.relacionamentos.models.Perfil;

public interface PerfilRepository extends MongoRepository<Perfil, String> {
    
}
