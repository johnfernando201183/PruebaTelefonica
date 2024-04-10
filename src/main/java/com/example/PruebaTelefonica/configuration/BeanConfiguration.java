package com.example.PruebaTelefonica.configuration;

import com.example.PruebaTelefonica.PruebaTelefonicaApplication;
import com.example.PruebaTelefonica.core.ports.ProductRepository;
import com.example.PruebaTelefonica.core.services.ProductService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = PruebaTelefonicaApplication.class)
public class BeanConfiguration {

  @Bean
  ProductService productService (
          final ProductRepository productRepository) {

      return new ProductService(productRepository);
  }
}
