package com.example.project.Controller;

import com.example.project.Client.Fakestore.FakeStoreProductDto;
import com.example.project.Dto.ProductDto;
import com.example.project.Exceptions.NotFoundException;
import com.example.project.Models.Category;
import com.example.project.Models.Product;
import com.example.project.Service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;

//    public ProductController(ProductService productService){
//        this.productService = productService;
//    }

    @GetMapping()
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Optional<Product>> getProductById(@PathVariable("productId") Long productId) throws NotFoundException {
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();

        headers.add(
                "auth-token", "noaccess4uheyhey"
        );

        Optional<Product> productOptional = productService.getProductByID(productId);
        if (productOptional.isEmpty()){
            throw new NotFoundException("No product with product id "+productId);
        }

        return new ResponseEntity<>(productService.getProductByID(productId),headers, HttpStatus.FOUND);
    }

    @PostMapping()
    public ResponseEntity<Product> addProduct(@RequestBody FakeStoreProductDto productDto) {
        Product newProduct = productService.addNewProduct(productDto);

        return ResponseEntity.ok(newProduct);
    }

    @PutMapping("/{productId}")
    public Product updateProduct(@PathVariable("productId") Long productId,@RequestBody FakeStoreProductDto productDto) {
        Product product = new Product();
        product.setId(productDto.getId());
        product.setCategory(new Category());
        product.getCategory().setName(productDto.getCategory());
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
        product.setDescription(productDto.getDescription());

        return productService.updateProduct(productId, product);
    }

    @DeleteMapping("/{productId}")
    public String deleteProduct(@PathVariable("productId") Long productId) {
        return "";
    }


}
