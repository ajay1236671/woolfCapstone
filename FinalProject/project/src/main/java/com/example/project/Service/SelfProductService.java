package com.example.project.Service;

import com.example.project.Client.Fakestore.FakeStoreProductDto;
import com.example.project.Exceptions.NotFoundException;
import com.example.project.Models.Category;
import com.example.project.Models.Product;
import com.example.project.Repository.ProductRepository;

import java.util.List;
import java.util.Optional;


//    optinal , exception handling, If list response is correct, add own method or scenarion
public class SelfProductService implements ProductService {

    ProductRepository productRepository;

    @Override
    public List<Product> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products;
    }

    @Override
    public Optional<Product> getProductByID(Long productId) throws NotFoundException {
        return Optional.empty();
    }

    @Override
    public Product addNewProduct(FakeStoreProductDto productDto) {
        return productRepository.save(convertFakeStoreProductDtoToProduct(productDto));
    }

    @Override
    public Product updateProduct(Long productId, Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product deleteProduct(Long productId) {
        return productRepository.deleteProductById(productId);
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


}
