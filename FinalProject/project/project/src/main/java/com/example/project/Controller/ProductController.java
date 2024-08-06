package com.example.project.Controller;

import com.example.project.Dto.ProductDto;
import com.example.project.Models.Product;
import com.example.project.Service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;

    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping()
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable("productId") Long productId) {
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();

        headers.add(
                "auth-token", "noaccess4uheyhey"
        );

        return new ResponseEntity<>(productService.getProductByID(productId),headers, HttpStatus.FOUND);
    }

    @PostMapping()
    public ResponseEntity<Product> addProduct(@RequestBody ProductDto productDto) {
        Product newProduct = productService.addNewProduct(productDto);

        return ResponseEntity.ok(newProduct);
    }

    @PutMapping("/{productId}")
    public String updateProduct(@PathVariable("productId") Long productId) {
        return "";
    }

    @DeleteMapping("/{productId}")
    public String deleteProduct(@PathVariable("productId") Long productId) {
        return "";
    }


}
