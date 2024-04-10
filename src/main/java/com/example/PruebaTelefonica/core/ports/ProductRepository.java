package com.example.PruebaTelefonica.core.ports;

import com.example.PruebaTelefonica.adapters.controllers.request.ProductRequest;
import com.example.PruebaTelefonica.adapters.controllers.response.MessageResponse;
import com.example.PruebaTelefonica.adapters.controllers.response.ProductResponse;
import com.example.PruebaTelefonica.core.domain.ProductEntity;
import com.example.PruebaTelefonica.core.util.PageSupport;
import org.springframework.data.domain.Pageable;
import org.springframework.http.codec.multipart.FilePart;
import reactor.core.publisher.Mono;

public interface ProductRepository {
  Mono<PageSupport<ProductEntity>> getAll(Pageable pageable);

  Mono<ProductResponse> getById(String id);

  Mono<ProductResponse> save(ProductRequest productRequest);

  Mono<MessageResponse> update(ProductRequest productRequest, String id);

  Mono<MessageResponse> deleteById(String id);

  Mono<ProductResponse> uploadImage(String id, FilePart filePart);

}
