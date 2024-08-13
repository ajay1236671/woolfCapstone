package com.example.project.Service;

import com.example.project.Client.Fakestore.FakeStoreClient;
import com.example.project.Client.Fakestore.FakeStoreProductDto;
import com.example.project.Dto.ProductDto;
import com.example.project.Models.Category;
import com.example.project.Models.Product;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FakeStoreProductServiceImpl implements ProductService {

    private RestTemplateBuilder restTemplateBuilder;
    private FakeStoreClient fakeStoreClient;

    public FakeStoreProductServiceImpl(RestTemplateBuilder restTemplateBuilder, FakeStoreClient fakeStoreClient) {
        this.restTemplateBuilder = restTemplateBuilder;
        this.fakeStoreClient = fakeStoreClient;
    }

    public <T> ResponseEntity<T> requestForEntity(HttpMethod httpMethod, String url, @Nullable Object request, Class<T> responseType, Object uriVariables) throws RestClientException {

        RestTemplate restTemplate = restTemplateBuilder.requestFactory(HttpComponentsClientHttpRequestFactory.class).build();
        RequestCallback requestCallback = restTemplate.httpEntityCallback(request, responseType);
        ResponseExtractor<ResponseEntity<T>> responseExtractor = restTemplate.responseEntityExtractor(responseType);
        return restTemplate.execute(url, httpMethod, requestCallback, responseExtractor, uriVariables);
    }

    private Product convertFakeStoreProductDtoToProduct(FakeStoreProductDto productDto) {
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
    public List<Product> getAllProducts() {
        RestTemplate restTemplate = restTemplateBuilder.build();
        List<FakeStoreProductDto> fakeStoreProductDtos = fakeStoreClient.getALlProducts();

//        ResponseEntity<FakeStoreProductDto[]> response = restTemplate.getForEntity("https://fakestoreapi.com/products", FakeStoreProductDto[].class);

        List<Product> products = new ArrayList<>();
        for (FakeStoreProductDto productDto : fakeStoreProductDtos) {
//            Product product = new Product();
//            product.setId(productDto.getId());
//            product.setTitle(productDto.getTitle());
//            product.setPrice(productDto.getPrice());
//            Category category = new Category();
//            category.setName(productDto.getCategory());
//            product.setCategory(category);
//            product.setImageUrl(productDto.getImage());
            products.add(convertFakeStoreProductDtoToProduct(productDto));
        }
        return products;
    }

    @Override
    public Optional<Product> getProductByID(Long productId) {
        RestTemplate restTemplate = restTemplateBuilder.build();

//        ResponseEntity<FakeStoreProductDto> response = restTemplate.getForEntity("https://fakestoreapi.com/products/{id}", FakeStoreProductDto.class, productId);
        FakeStoreProductDto productDto = fakeStoreClient.getSingleProduct(productId);
//        Product product = new Product();
//        product.setId(productDto.getId());
//        product.setTitle(productDto.getTitle());
//        product.setPrice(productDto.getPrice());
//        Category category = new Category();
//        category.setName(productDto.getCategory());
//        product.setCategory(category);
//        product.setImageUrl(productDto.getImage());
        return Optional.of(convertFakeStoreProductDtoToProduct(productDto));
    }

    @Override
    public Product addNewProduct(FakeStoreProductDto product) {

        FakeStoreProductDto productDto = fakeStoreClient.addNewProduct(product);

//        Product product1 = new Product();
//        product1.setId(productDto.getId());
//        product1.setTitle(productDto.getTitle());
//        product1.setPrice(productDto.getPrice());
//        Category category = new Category();
//        category.setName(productDto.getCategory());
//        product1.setCategory(category);
//        product1.setImageUrl(productDto.getImage());

        return convertFakeStoreProductDtoToProduct(productDto);
    }

    @Override
    public Product updateProduct(Long productId, Product product) {
        RestTemplate restTemplate = restTemplateBuilder.requestFactory(HttpComponentsClientHttpRequestFactory.class).build();

        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setDescription(product.getDescription());
        fakeStoreProductDto.setImage(product.getImageUrl());
        fakeStoreProductDto.setPrice(product.getPrice());
        fakeStoreProductDto.setTitle(product.getTitle());
        fakeStoreProductDto.setCategory(product.getCategory().getName());

        FakeStoreProductDto fakeStoreProductDtoResponseEntity = restTemplate.patchForObject("https://fakestoreapi.com/products/{id}",
                fakeStoreProductDto,
                FakeStoreProductDto.class,
                productId);

//        ResponseEntity<FakeStoreProductDto> fakeStoreProductDtoResponseEntity = requestForEntity(HttpMethod.PATCH, "https://fakestoreapi.com/products/{id}", fakeStoreProductDto, FakeStoreProductDto.class, productId);
        return convertFakeStoreProductDtoToProduct(fakeStoreProductDtoResponseEntity);
    }


    @Override
    public Product deleteProduct(Long productId) {
        return null;
    }


}
