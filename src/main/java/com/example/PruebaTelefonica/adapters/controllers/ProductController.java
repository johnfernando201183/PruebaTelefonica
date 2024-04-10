package com.example.PruebaTelefonica.adapters.controllers;

import com.example.PruebaTelefonica.adapters.controllers.request.ProductRequest;
import com.example.PruebaTelefonica.adapters.controllers.response.MessageResponse;
import com.example.PruebaTelefonica.adapters.controllers.response.ProductResponse;
import com.example.PruebaTelefonica.core.domain.ProductEntity;
import com.example.PruebaTelefonica.core.services.ProductService;
import com.example.PruebaTelefonica.core.util.Enums;
import com.example.PruebaTelefonica.core.util.PageSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

/**
 * <b>Class</b>: ProductController <br/>
 * <b>Copyright</b>: 2024 Pacifico Seguros - La Chakra <br/>.
 *
 * @author 2024  Pacifico Seguros - La Chakra <br/>
 * <u>Service Provider</u>: Teamsoft <br/>
 * <u>Developed by</u>: John Salazar <br/>
 * <u>Changes:</u><br/>
 * <ul>
 *   <li>
 *     Abril 08, 2024 Creación de Clase.
 *   </li>
 * </ul>
 */
@RestController
@RequestMapping("/api/products")
public class ProductController {
  private static final Logger log = LoggerFactory.getLogger(ProductController.class);
  @Autowired
  private ProductService productService;

  @GetMapping
  public Mono<PageSupport<ProductEntity>> getAllProducts(
          @RequestParam(name = "page", defaultValue = PageSupport.FIRST_PAGE_NUM) int page,
          @RequestParam(name = "size", defaultValue = PageSupport.DEFAULT_PAGE_SIZE) int size) {
    return productService.getAll(PageRequest.of(page, size));
  }

  @GetMapping("/{id}")
  public Mono<ResponseEntity<ProductResponse>> getProductById(@PathVariable("id") String id) {
    return productService.getById(id)
            .map(p -> ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(p)
            ).defaultIfEmpty(ResponseEntity.notFound().build());
  }

  @PostMapping
  public Mono<ResponseEntity<ProductResponse>> createProduct(@Validated @RequestBody ProductRequest productRequest) {
    return productService.save(productRequest)
            .map(p -> ResponseEntity.status(HttpStatus.CREATED)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(p));
  }

  @PutMapping("/{id}")
  public Mono<ResponseEntity<MessageResponse>> updateProduct(@Validated @RequestBody ProductRequest productRequest,
                                                             @PathVariable("id") String id) {
    return productService.update(productRequest, id)
            .map(m -> {
              if (m.getCode().equals(Enums.PRODUCT_NOT_FOUND.getCode())) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(m);
              }
              return ResponseEntity.ok()
                      .contentType(MediaType.APPLICATION_JSON)
                      .body(m);
            });
  }

  @DeleteMapping("/{id}")
  public Mono<ResponseEntity<MessageResponse>> deleteById(@PathVariable String id) {
    return productService.deleteById(id)
            .map(m -> {
              if (m.getCode().equals(Enums.PRODUCT_NOT_FOUND.getCode())) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(m);
              }
              return ResponseEntity.ok()
                      .contentType(MediaType.APPLICATION_JSON)
                      .body(m);
            });
  }

  @PostMapping("/upload/{id}")
  public Mono<ResponseEntity<ProductResponse>> uploadImage(@PathVariable("id") String id, @RequestPart FilePart file) {
    return productService.uploadImage(id, file).
            map(p -> ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(p))
            .defaultIfEmpty(ResponseEntity.notFound().build());
  }
}
