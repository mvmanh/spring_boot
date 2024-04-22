package com.mvm.app;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/")
public class ProductController {

    // Simulate in-memory database with a static list
    private static List<Product> products = new ArrayList<>();

    @GetMapping("/test")
    public String test() {
        return "Test if code update when push to git";
    }

    // Get all products
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    // Get product by ID
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") Long id) {
        Optional<Product> product = products.stream().filter(p -> p.getId().equals(id)).findFirst();
        return product.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Create a new product
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        product.setId(generateProductId());
        products.add(product);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    // Update a product with PUT
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") Long id, @RequestBody Product product) {
        Optional<Product> existingProduct = products.stream().filter(p -> p.getId().equals(id)).findFirst();
        if (existingProduct.isPresent()) {
            product.setId(id);
            products.removeIf(p -> p.getId().equals(id));
            products.add(product);
            return new ResponseEntity<>(product, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Update a product with PATCH
    @PatchMapping("/{id}")
    public ResponseEntity<Product> partialUpdateProduct(@PathVariable("id") Long id, @RequestBody Product product) {
        Optional<Product> existingProduct = products.stream().filter(p -> p.getId().equals(id)).findFirst();
        if (existingProduct.isPresent()) {
            Product existing = existingProduct.get();
            if (product.getName() != null) {
                existing.setName(product.getName());
            }
            if (product.getPrice() != 0) {
                existing.setPrice(product.getPrice());
            }
            if (product.getColor() != null) {
                existing.setColor(product.getColor());
            }
            return new ResponseEntity<>(existing, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete a product
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteProduct(@PathVariable("id") Long id) {
        boolean removed = products.removeIf(p -> p.getId().equals(id));
        if (removed) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Method to generate a unique product ID (for demonstration purposes)
    private Long generateProductId() {
        Long maxId = products.stream().mapToLong(Product::getId).max().orElse(0L);
        return maxId + 1;
    }
}
