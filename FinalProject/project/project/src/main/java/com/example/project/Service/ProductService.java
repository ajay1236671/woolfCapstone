package com.example.project.Service;

import com.example.project.Client.Fakestore.FakeStoreProductDto;
import com.example.project.Dto.ProductDto;
import com.example.project.Exceptions.NotFoundException;
import com.example.project.Models.Category;
import com.example.project.Models.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> getAllProducts();

//    Product getProductByID(Long productId);
    Optional<Product> getProductByID(Long productId) throws NotFoundException;

    Product addNewProduct(FakeStoreProductDto productDto);

    Product updateProduct(Long productId,Product product);

    Product deleteProduct(Long productId);
}
