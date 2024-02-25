package com.desafioanotaai.controllers;

import com.desafioanotaai.dtos.CategoryDto;
import com.desafioanotaai.exceptions.CategoryNotFoundException;
import com.desafioanotaai.models.Category;
import com.desafioanotaai.services.CategoryService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public ResponseEntity<Category> insertCategory(@RequestBody CategoryDto categoryDto){
        Category category = categoryService.insert(categoryDto);
        return ResponseEntity.ok().body(category);
    }

    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories(){
        List<Category> categories = categoryService.getAll();
        return ResponseEntity.ok().body(categories);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable("id") String id, @RequestBody CategoryDto categoryDto) throws CategoryNotFoundException {
        Category updatedCategory = categoryService.update(id,categoryDto);
        return ResponseEntity.ok().body(updatedCategory);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Category> deleteCategory(@PathVariable("id") String id) throws CategoryNotFoundException {
        categoryService.delete(id);
        return ResponseEntity.ok().build();
    }

}
