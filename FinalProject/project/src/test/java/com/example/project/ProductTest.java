package com.example.project;

import com.example.project.Models.Category;
import com.example.project.Models.Product;
import com.example.project.Repository.CategoryRepository;
import com.example.project.Repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ProductTest {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ProductRepository productRepository;

    @Test
    void saveProductAndCategory() {
        Category category = new Category();
        category.setName("phones");
        Category savedCategory = categoryRepository.save(category);

        Product product = new Product();
        product.setPrice(100);
        product.setImageUrl("hello");
        product.setCategory(category);
        productRepository.save(product);
    }

    @Test
    void fetchTest(){
        Product product = productRepository.findProductById(2L);
        System.out.println("Fetched Product");

        Category category = product.getCategory();
        String name = category.getName();
        System.out.println(category.getName());
    }


    @Test
    @Transactional
    void testingLoadinsEagerAndEasy(){
//        Product product = productRepository.findProductById(2L);
//        System.out.println("Fetched Product");
//
//        Category category = product.getCategory();
//        String name = category.getName();
//        System.out.println(category.getName());

        Category category = categoryRepository.findCategoryById(1L);
        Product productj = category.getProducts().get(0);
        System.out.println("loaded");


        Product product = productRepository.findProductById(2L);
        System.out.println("loaded again");


    }


}
