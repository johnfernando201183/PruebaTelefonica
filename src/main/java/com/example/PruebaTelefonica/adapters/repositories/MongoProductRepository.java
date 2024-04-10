package com.example.PruebaTelefonica.adapters.repositories;

import com.example.PruebaTelefonica.adapters.controllers.request.ProductRequest;
import com.example.PruebaTelefonica.adapters.controllers.response.MessageResponse;
import com.example.PruebaTelefonica.adapters.controllers.response.ProductResponse;
import com.example.PruebaTelefonica.core.builder.ProductBuilder;
import com.example.PruebaTelefonica.core.domain.ProductEntity;
import com.example.PruebaTelefonica.core.ports.ProductRepository;
import com.example.PruebaTelefonica.core.util.Constants;
import com.example.PruebaTelefonica.core.util.Enums;
import com.example.PruebaTelefonica.core.util.PageSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Pageable;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.File;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * <b>Class</b>: MongoProductRepository <br/>
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
@Component
@Primary
public class MongoProductRepository implements ProductRepository {

  private SpringDataMongoProductRepository productRepository;
  @Value("${config.uploads.path}")
  private String path;

  @Autowired
  public MongoProductRepository(SpringDataMongoProductRepository productRepository){
    this.productRepository = productRepository;
  }

  @Override
  public Mono<PageSupport<ProductEntity>> getAll(Pageable pageable) {
    return productRepository.findAll()
            .collectList()
            .map(productEntities -> new PageSupport<>(
                    productEntities
                            .stream()
                            .skip(pageable.getPageNumber() * pageable.getPageSize())
                            .limit(pageable.getPageSize())
                            .collect(Collectors.toList()),
                    pageable.getPageNumber(), pageable.getPageSize(), (long) productEntities.size()));
  }

  @Override
  public Mono<ProductResponse> getById(String id) {
    return productRepository.findById(id)
            .map(ProductBuilder::entityToResponse);
  }

  @Override
  public Mono<ProductResponse> save(ProductRequest productRequest) {
    return productRepository.save(ProductBuilder.createProduct(productRequest))
            .map(ProductBuilder::entityToResponse);
  }

  @Override
  public Mono<MessageResponse> update(ProductRequest productRequest, String id) {
    return productRepository.findById(id)
            .flatMap(productEntity -> productRepository.save(
                    ProductBuilder.updateProduct(productEntity, productRequest)
            ).flatMap(productEntity1 -> Mono.just(MessageResponse
                    .builder()
                    .code(Enums.PRODUCT_UPDATED_SUCCESSFUL.getCode())
                    .message(Enums.PRODUCT_UPDATED_SUCCESSFUL.getDescription() + productEntity1.getId())
                    .build())))
            .switchIfEmpty(Mono.just(MessageResponse
                    .builder()
                    .code(Enums.PRODUCT_NOT_FOUND.getCode())
                    .message(Enums.PRODUCT_NOT_FOUND.getDescription() + id)
                    .build())
            );
  }

  @Override
  public Mono<MessageResponse> deleteById(String id) {
    return productRepository.findById(id)
            .flatMap(productEntity -> productRepository.deleteById(id)
                    .then(Mono.just(MessageResponse.builder()
                            .code(Enums.PRODUCT_DELETED.getCode())
                            .message(Enums.PRODUCT_DELETED.getDescription())
                            .build())))
            .switchIfEmpty(Mono.just(MessageResponse
                    .builder()
                    .code(Enums.PRODUCT_NOT_FOUND.getCode())
                    .message(Enums.PRODUCT_NOT_FOUND.getDescription() + id)
                    .build()));
  }

  @Override
  public Mono<ProductResponse> uploadImage(String id, FilePart filePart) {
    return productRepository.findById(id)
            .flatMap(productEntity -> {
              productEntity.setImage(UUID.randomUUID().toString() + Constants.replaceAll(filePart.filename()));

              return filePart.transferTo(new File(path + productEntity.getImage()))
                      .then(productRepository.save(productEntity));
            }).flatMap(p -> Mono.just(ProductBuilder.entityToResponse(p)));
  }

}
