package com.example.PruebaTelefonica.core.services;

import com.example.PruebaTelefonica.adapters.controllers.request.ProductRequest;
import com.example.PruebaTelefonica.adapters.controllers.response.MessageResponse;
import com.example.PruebaTelefonica.adapters.controllers.response.ProductResponse;
import com.example.PruebaTelefonica.core.builder.ProductBuilder;
import com.example.PruebaTelefonica.core.domain.ProductEntity;
import com.example.PruebaTelefonica.core.ports.ProductRepository;
import com.example.PruebaTelefonica.core.ports.ProductUseCase;
import com.example.PruebaTelefonica.core.util.Constants;
import com.example.PruebaTelefonica.core.util.Enums;
import com.example.PruebaTelefonica.core.util.PageSupport;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.http.codec.multipart.FilePart;
import reactor.core.publisher.Mono;


import java.io.File;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * <b>Class</b>: ProductService <br/>
 * <b>Copyright</b>: 2024 Pacifico Seguros - La Chakra <br/>.
 *
 * @author 2024  Pacifico Seguros - La Chakra <br/>
 * <u>Service Provider</u>: Teamsoft <br/>
 * <u>Developed by</u>: John Salazar <br/>
 * <u>Changes:</u><br/>
 * <ul>
 *   <li>
 *     Abril 09, 2024 Creación de Clase.
 *   </li>
 * </ul>
 */
public class ProductService implements ProductUseCase {

  ProductRepository productRepository;

  public ProductService(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  @Override
  public Mono<PageSupport<ProductEntity>> getAll(Pageable pageable) {
    return productRepository.getAll(pageable);
  }

  @Override
  public Mono<ProductResponse> getById(String id) {
    return productRepository.getById(id);
  }

  @Override
  public Mono<ProductResponse> save(ProductRequest productRequest) {
    return productRepository.save(productRequest);
  }

  @Override
  public Mono<MessageResponse> update(ProductRequest productRequest, String id) {
    return productRepository.update(productRequest, id);
  }

  @Override
  public Mono<MessageResponse> deleteById(String id) {
    return productRepository.deleteById(id);
  }

  @Override
  public Mono<ProductResponse> uploadImage(String id, FilePart filePart) {
    return productRepository.uploadImage(id, filePart);
  }
}
