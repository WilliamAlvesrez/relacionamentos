package com.william.mongodb.relacionamentos.relacionamentos.repository;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.william.mongodb.relacionamentos.relacionamentos.models.Curso;

public interface CursoRepository extends MongoRepository<Curso, String> {
    
}
