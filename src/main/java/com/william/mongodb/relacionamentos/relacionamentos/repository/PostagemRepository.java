package com.william.mongodb.relacionamentos.relacionamentos.repository;
import com.william.mongodb.relacionamentos.relacionamentos.models.Postagem;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PostagemRepository extends MongoRepository<Postagem, String> {
    
}
