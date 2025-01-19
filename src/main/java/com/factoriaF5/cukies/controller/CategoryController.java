package com.factoriaF5.cukies.controller;

import com.factoriaF5.cukies.DTOs.category.CategoryDTO;
import com.factoriaF5.cukies.exception.EmptyException;
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
    public ResponseEntity<Optional<CategoryDTO>> getCategoryById(@PathVariable int id){
    return new ResponseEntity<>(categoryService.findCategoryById(id), HttpStatus.OK);

    }
    @PutMapping("/{id}")
    public ResponseEntity<CategoryDTO> updateCategory(@PathVariable int id, @Valid @RequestBody CategoryDTO categoryDTO){
        try {
            CategoryDTO updatedCategoryDTO = categoryService.updateCategory(id, categoryDTO);
            return new ResponseEntity<>(updatedCategoryDTO, HttpStatus.OK);
        } catch (EmptyException exception){
            //personalizar exception
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable int id){
        try {
            categoryService.deleteCategory(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (EmptyException exception){
            //personalizar exception
            return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
