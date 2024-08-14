package com.example.project.Controller;

import com.example.project.Client.AuthenticationClient.AuthenticationClient;
import com.example.project.Client.AuthenticationClient.Dtos.Role;
import com.example.project.Client.AuthenticationClient.Dtos.SessionStatus;
import com.example.project.Client.AuthenticationClient.Dtos.ValidateTokenResponseDto;
import com.example.project.Client.Fakestore.FakeStoreProductDto;
import com.example.project.Dto.ProductDto;
import com.example.project.Exceptions.NotFoundException;
import com.example.project.Models.Category;
import com.example.project.Models.Product;
import com.example.project.Service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {


    private ProductService productService;
    private AuthenticationClient authenticationClient;

    public ProductController(ProductService productService, AuthenticationClient authenticationClient) {
        this.productService = productService;
        this.authenticationClient = authenticationClient;
    }


//    @GetMapping()
//    public List<Product> getAllProductss() {
//        return productService.getAllProducts();
//    }


    //    Make only admin to access product
    @GetMapping()
    public ResponseEntity<List<Product>> getAllProducts(@Nullable @RequestHeader("AUTH_TOKEN") String token,
                                                        @Nullable @RequestHeader("USER_ID") Long userId) {

        if (token == null || userId == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        ValidateTokenResponseDto response = authenticationClient.validate(token, userId);

        if (response.getSessionStatus().equals(SessionStatus.INVALID)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        boolean isAdmin = false;

        for (Role role : response.getUserDto().getRoles()) {
            if (role.getName().equalsIgnoreCase("admin")) {
                isAdmin = true;
            }
        }

        if (!isAdmin) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        List<Product> products = productService.getAllProducts();

        return new ResponseEntity<>(products, HttpStatus.OK);
    }


    @GetMapping("/{productId}")
    public ResponseEntity<Optional<Product>> getProductById(@PathVariable("productId") Long productId) throws NotFoundException {
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();

        headers.add(
                "auth-token", "noaccess4uheyhey"
        );

        Optional<Product> productOptional = productService.getProductByID(productId);
        if (productOptional.isEmpty()) {
            throw new NotFoundException("No product with product id " + productId);
        }

        return new ResponseEntity<>(productService.getProductByID(productId), headers, HttpStatus.FOUND);
    }

    @PostMapping()
    public ResponseEntity<Product> addProduct(@RequestBody FakeStoreProductDto productDto) {
        Product newProduct = productService.addNewProduct(productDto);

        return ResponseEntity.ok(newProduct);
    }

    @PutMapping("/{productId}")
    public Product updateProduct(@PathVariable("productId") Long productId, @RequestBody FakeStoreProductDto productDto) {
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
