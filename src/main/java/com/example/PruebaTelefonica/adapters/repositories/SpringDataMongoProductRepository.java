package com.example.PruebaTelefonica.adapters.repositories;


import com.example.PruebaTelefonica.core.domain.ProductEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataMongoProductRepository extends ReactiveMongoRepository<ProductEntity, String> {
}
