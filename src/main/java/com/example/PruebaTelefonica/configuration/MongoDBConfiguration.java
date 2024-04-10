package com.example.PruebaTelefonica.configuration;

import com.example.PruebaTelefonica.adapters.repositories.SpringDataMongoProductRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories(basePackageClasses = SpringDataMongoProductRepository.class)
public class MongoDBConfiguration {
}
