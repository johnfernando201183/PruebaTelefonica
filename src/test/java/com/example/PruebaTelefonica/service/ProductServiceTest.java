package com.example.PruebaTelefonica.service;

import com.example.PruebaTelefonica.adapters.controllers.request.ProductRequest;
import com.example.PruebaTelefonica.adapters.controllers.response.MessageResponse;
import com.example.PruebaTelefonica.adapters.controllers.response.ProductResponse;
import com.example.PruebaTelefonica.core.domain.ProductEntity;
import com.example.PruebaTelefonica.core.ports.ProductRepository;
import com.example.PruebaTelefonica.core.services.ProductService;
import com.example.PruebaTelefonica.core.util.PageSupport;
import com.example.PruebaTelefonica.functions.ModelResponse;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

/**
 * <b>Class</b>: ProductServiceTest <br/>
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
@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

  @InjectMocks
  private ProductService service;
  @Mock
  private ProductRepository repository;

  private static ProductResponse productResponse;
  private static ProductRequest productRequest;
  private static MessageResponse updateMessageResponse;
  private static MessageResponse deleteMessageResponse;

  @BeforeAll
  public static void setupInit() {
    productResponse = ModelResponse.productResponse();
    productRequest = ModelResponse.productRequest();
    updateMessageResponse = ModelResponse.updateMessageResponse();
    deleteMessageResponse = ModelResponse.deleteMessageResponse();
  }

  @Test
  public void getProductsTest() {
    Pageable pageable = Mockito.mock(Pageable.class);
    List<ProductEntity> list = new ArrayList<>(Arrays.asList(ProductEntity.builder().build()));
    long totalElements = 100;
    PageSupport<ProductEntity> expectedPageSupport = new PageSupport<>(list, 1,2, totalElements);
    Mockito.when(repository.getAll(pageable)).thenReturn(Mono.just(expectedPageSupport));
    Mono<PageSupport<ProductEntity>> result = service.getAll(pageable);
    StepVerifier.create(result).expectNextCount(1).verifyComplete();
  }

  @Test
  void getProductById() {
    when(service.getById(any())).thenReturn(Mono.just(ProductResponse.builder().build()));
    Mono<ProductResponse> response = service.getById("sd");
    StepVerifier.create(response).expectNextCount(1).verifyComplete();
  }

  @Test
  void saveProduct() {
    when(service.save(any())).thenReturn(Mono.just(ProductResponse.builder().build()));
    Mono<ProductResponse> response = service.save(productRequest);
    StepVerifier.create(response).expectNextCount(1).verifyComplete();
  }

  @Test
  void updateProduct() {
    when(service.update(any(), any())).thenReturn(Mono.just(MessageResponse.builder().build()));
    Mono<MessageResponse> response = service.update(productRequest, "asdasd");
    StepVerifier.create(response).expectNextCount(1).verifyComplete();
  }

  @Test
  void deleteProduct() {
    when(service.deleteById(any())).thenReturn(Mono.just(MessageResponse.builder().build()));
    Mono<MessageResponse> response = service.deleteById("ss");
    StepVerifier.create(response).expectNextCount(1).verifyComplete();
  }

}
