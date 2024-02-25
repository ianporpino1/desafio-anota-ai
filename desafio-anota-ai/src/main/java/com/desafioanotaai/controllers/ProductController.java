package com.desafioanotaai.controllers;

import com.desafioanotaai.dtos.CategoryDto;
import com.desafioanotaai.dtos.ProductDto;
import com.desafioanotaai.exceptions.CategoryNotFoundException;
import com.desafioanotaai.exceptions.ProductNotFoundException;
import com.desafioanotaai.models.Category;
import com.desafioanotaai.models.Product;
import com.desafioanotaai.services.CategoryService;
import com.desafioanotaai.services.ProductService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<Product> insertCategory(@RequestBody ProductDto productDto) throws CategoryNotFoundException {
        Product product = productService.insert(productDto);
        return ResponseEntity.ok().body(product);
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllCategories(){
        List<Product> products = productService.getAll();
        return ResponseEntity.ok().body(products);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateCategory(@PathVariable("id") String id, @RequestBody ProductDto productDto) throws ProductNotFoundException, CategoryNotFoundException {
        Product updatedProduct = productService.update(id,productDto);
        return ResponseEntity.ok().body(updatedProduct);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Category> deleteCategory(@PathVariable("id") String id) throws ProductNotFoundException {
        productService.delete(id);
        return ResponseEntity.ok().build();
    }
}
