package com.example.project.Service;

import com.example.project.Dto.ProductDto;
import com.example.project.Models.Category;
import com.example.project.Models.Product;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeStoreProductServiceImpl implements ProductService {

    private RestTemplateBuilder restTemplateBuilder;

    public FakeStoreProductServiceImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }

    @Override
    public List<Product> getAllProducts() {
        RestTemplate restTemplate = restTemplateBuilder.build();

        ResponseEntity<ProductDto[]> response = restTemplate.getForEntity("https://fakestoreapi.com/products", ProductDto[].class);

        List<Product> products = new ArrayList<>();
        for (ProductDto productDto : response.getBody()) {
            Product product = new Product();
            product.setId(productDto.getId());
            product.setTitle(productDto.getTitle());
            product.setPrice(productDto.getPrice());
            Category category = new Category();
            category.setName(productDto.getCategory());
            product.setCategory(category);
            product.setImageUrl(productDto.getImage());
            products.add(product);
        }
        return products;
    }

    @Override
    public Product getProductByID(Long productId) {
        RestTemplate restTemplate = restTemplateBuilder.build();

        ResponseEntity<ProductDto> response = restTemplate.getForEntity("https://fakestoreapi.com/products/{id}", ProductDto.class, productId);
        ProductDto productDto = response.getBody();
        Product product = new Product();
        product.setId(productDto.getId());
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
        Category category = new Category();
        category.setName(productDto.getCategory());
        product.setCategory(category);
        product.setImageUrl(productDto.getImage());
        return product;

    }

    @Override
    public Product addNewProduct(ProductDto product) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<ProductDto> response = restTemplate.postForEntity(
                "https://fakestoreapi.com/products",
                product,
                ProductDto.class
        );

        ProductDto productDto = response.getBody();

        Product product1 = new Product();
        product1.setId(productDto.getId());
        product1.setTitle(productDto.getTitle());
        product1.setPrice(productDto.getPrice());
        Category category = new Category();
        category.setName(productDto.getCategory());
        product1.setCategory(category);
        product1.setImageUrl(productDto.getImage());

        return product1;
    }

    @Override
    public Product updateProduct(Long productId) {
        return null;
    }

    @Override
    public Product deleteProduct(Long productId) {
        return null;
    }
}
