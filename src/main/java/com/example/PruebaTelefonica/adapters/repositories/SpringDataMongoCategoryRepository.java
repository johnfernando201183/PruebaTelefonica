package com.example.PruebaTelefonica.adapters.repositories;

import com.example.PruebaTelefonica.core.domain.CategoryEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataMongoCategoryRepository extends ReactiveMongoRepository<CategoryEntity, String> {
}
