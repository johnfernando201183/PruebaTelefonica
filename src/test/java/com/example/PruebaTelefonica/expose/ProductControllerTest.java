package com.example.PruebaTelefonica.expose;

import com.example.PruebaTelefonica.adapters.controllers.request.ProductRequest;
import com.example.PruebaTelefonica.adapters.controllers.response.MessageResponse;
import com.example.PruebaTelefonica.adapters.controllers.response.ProductResponse;
import com.example.PruebaTelefonica.core.services.ProductService;
import com.example.PruebaTelefonica.core.util.PageSupport;
import com.example.PruebaTelefonica.functions.ModelResponse;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureWebTestClient
public class ProductControllerTest {


    private static final String URL_BASE = "/api";
    private static final String URL_GET_PRODUCTS = URL_BASE + "/products";
    private static final String URL_GET_PRODUCT_BY_ID = URL_BASE + "/products/{id}";
    private static final String URL_POST_PRODUCT = URL_BASE + "/products";
    private static final String URL_PUT_PRODUCT = URL_BASE + "/products/{id}";
    private static final String URL_DELETE_PRODUCT = URL_BASE + "/products/{id}";

    private static ProductResponse productResponse;
    private static ProductRequest productRequest;
    private static MessageResponse updateMessageResponse;
    private static MessageResponse deleteMessageResponse;


    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private ProductService productService;

    @BeforeAll
    public static void setupInit() {
        productResponse = ModelResponse.productResponse();
        productRequest = ModelResponse.productRequest();
        updateMessageResponse = ModelResponse.updateMessageResponse();
        deleteMessageResponse = ModelResponse.deleteMessageResponse();
    }

    @Test
    public void getProductsTest() {
        webTestClient.get()
                .uri(uriBuilder -> uriBuilder.path(URL_GET_PRODUCTS)
                        .queryParam("page", 0)
                        .queryParam("size", 2)
                        .build())
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody(PageSupport.class);
    }

    @Test
    public void getProductById() {
        when(productService.getById(anyString())).thenReturn(Mono.just(productResponse));

        String idProduct = "5f2e3236dcbfb9008c3982cc";

        webTestClient.get()
                .uri(URL_GET_PRODUCT_BY_ID, Collections.singletonMap("id", idProduct))
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody(ProductResponse.class);

    }

    @Test
    public void saveProduct() {
        when(productService.save(productRequest)).thenReturn(Mono.just(productResponse));

        webTestClient.post()
                .uri(URL_POST_PRODUCT)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(productRequest), ProductRequest.class)
                .exchange()
                .expectStatus().isCreated()
                .expectBody()
                .jsonPath("$.id").isNotEmpty();

    }

    @Test
    public void updateProduct() {

        String idProduct = "5f2e3236dcbfb9008c3982cceeeee";

        when(productService.update(productRequest, idProduct)).thenReturn(Mono.just(updateMessageResponse));

        webTestClient.put()
                .uri(URL_PUT_PRODUCT, Collections.singletonMap("id", idProduct))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(productRequest), ProductRequest.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody(MessageResponse.class);
    }

    @Test
    public void deleteProduct() {
        String idProduct = "5f2e3236dcbfb9008c3982cc";
        when(productService.getById(idProduct)).thenReturn(Mono.just(productResponse));
        when(productService.deleteById(productResponse.id)).thenReturn(Mono.just(deleteMessageResponse));

        webTestClient.delete()
                .uri(URL_DELETE_PRODUCT, Collections.singletonMap("id", idProduct))
                .exchange()
                .expectStatus().isOk()
                .expectBody(MessageResponse.class);
    }


}
