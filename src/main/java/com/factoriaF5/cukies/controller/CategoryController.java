package com.factoriaF5.cukies.controller;

import com.factoriaF5.cukies.DTOs.category.CategoryDTO;
import com.factoriaF5.cukies.exception.EmptyException;
import com.factoriaF5.cukies.model.Category;
import com.factoriaF5.cukies.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public ResponseEntity<CategoryDTO> saveCategory(@Valid @RequestBody CategoryDTO categoryDTO){
        CategoryDTO newCategoryDTO = categoryService.createCategory(categoryDTO);
        return new ResponseEntity<>(newCategoryDTO, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> getCategories(){
        return new ResponseEntity<>(categoryService.getCategories(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable int id){
        Optional<CategoryDTO> categoryDTO = categoryService.getCategoryById(id);
        if (categoryDTO.isPresent()){
            return ResponseEntity.ok(categoryDTO.get());
        }
        return ResponseEntity.notFound().build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<CategoryDTO> updateCategory(@PathVariable int id, @Valid @RequestBody CategoryDTO categoryDTO){
        try {
            CategoryDTO updatedCategory = categoryService.updateCategory(id, categoryDTO);
            return ResponseEntity.ok(updatedCategory);
        } catch (EmptyException exception){
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable int id){
        try {
            categoryService.deleteCategory(id);
            return ResponseEntity.noContent().build();
        } catch (EmptyException exception){
            return  ResponseEntity.notFound().build();
        }
    }

}
