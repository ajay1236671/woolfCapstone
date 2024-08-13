package com.example.project.Repository;

import com.example.project.Models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Product save(Product product);

    Product findProductById(Long id);
    Product deleteProductById(Long id);
//    Category findCategoryById(Long id); check if it works
}
