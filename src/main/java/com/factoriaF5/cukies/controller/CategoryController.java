package com.factoriaF5.cukies.controller;

import com.factoriaF5.cukies.DTOs.category.CategoryDTORequest;
import com.factoriaF5.cukies.DTOs.category.CategoryDTOResponse;
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
    public ResponseEntity<CategoryDTOResponse> saveCategory(@Valid @RequestBody CategoryDTORequest categoryDTORequest){
        CategoryDTOResponse newCategoryDTOResponse = categoryService.createCategory(categoryDTORequest);
        return new ResponseEntity<>(newCategoryDTOResponse, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CategoryDTOResponse>> getCategories(){
        return new ResponseEntity<>(categoryService.getCategories(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Optional<CategoryDTOResponse>> getCategoryById(@PathVariable int id){
    return new ResponseEntity<>(categoryService.findCategoryById(id), HttpStatus.OK);

    }
    @PutMapping("/{id}")
    public ResponseEntity<CategoryDTOResponse> updateCategory(@PathVariable int id, @Valid @RequestBody CategoryDTORequest categoryDTORequest){
        try {
            CategoryDTOResponse updatedCategoryDTO = categoryService.updateCategory(id, categoryDTORequest);
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
