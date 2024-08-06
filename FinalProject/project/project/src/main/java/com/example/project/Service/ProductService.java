package com.example.project.Service;

import com.example.project.Dto.ProductDto;
import com.example.project.Models.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();

    Product getProductByID(Long productId);

    Product addNewProduct(ProductDto productDto);

    Product updateProduct(Long productId);

    Product deleteProduct(Long productId);
}
