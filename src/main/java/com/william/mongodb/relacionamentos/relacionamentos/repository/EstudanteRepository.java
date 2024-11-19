package com.william.mongodb.relacionamentos.relacionamentos.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.william.mongodb.relacionamentos.relacionamentos.models.Estudante;


public interface EstudanteRepository extends MongoRepository<Estudante, String> {
    
}