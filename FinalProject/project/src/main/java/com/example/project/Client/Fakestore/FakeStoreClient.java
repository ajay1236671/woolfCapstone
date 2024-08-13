package com.example.project.Client.Fakestore;

import com.example.project.Dto.ProductDto;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Component
public class FakeStoreClient {
    private RestTemplateBuilder restTemplateBuilder;

    public FakeStoreClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }

    public List<FakeStoreProductDto> getALlProducts() {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto[]> response = restTemplate.getForEntity("https://fakestoreapi.com/products", FakeStoreProductDto[].class);
        return Arrays.asList(response.getBody());
    }

    public FakeStoreProductDto getSingleProduct(Long productId) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> response = restTemplate.getForEntity(
                "https://fakestoreapi.com/products/{id}", FakeStoreProductDto.class, productId);
        return response.getBody();

    }

    public FakeStoreProductDto addNewProduct(FakeStoreProductDto product) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> response = restTemplate.postForEntity(
                "https://fakestoreapi.com/products",
                product,
                FakeStoreProductDto.class
        );
        return response.getBody();
    }

    public String updateProduct(FakeStoreProductDto fakeStoreProductDto) {
        return null;
    }

    public String replaceProduct(FakeStoreProductDto fakeStoreProductDto) {
        return null;
    }

    public String deleteProduct(Long id) {
        return null;
    }
}
