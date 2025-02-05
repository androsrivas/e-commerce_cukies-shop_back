package com.factoriaF5.cukies.controller;

import com.factoriaF5.cukies.DTOs.product.ProductDTORequest;
import com.factoriaF5.cukies.DTOs.product.ProductDTOResponse;
import com.factoriaF5.cukies.exception.category.CategoryNotFoundException;
import com.factoriaF5.cukies.exception.product.ProductNotFoundException;
import com.factoriaF5.cukies.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<ProductDTOResponse>> getAll(){
        return ResponseEntity.ok(productService.getProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTOResponse> getProductById(@PathVariable int id) {
        return ResponseEntity.ok(productService.findProductById(id));
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ProductDTOResponse> createProduct(
            @Valid @RequestPart("productDTORequest") ProductDTORequest productDTORequest,
            @RequestPart("image") MultipartFile image
    ) {
        ProductDTOResponse newProductDTOResponse = productService.createProduct(productDTORequest, image);
        return new ResponseEntity<>(newProductDTOResponse, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDTOResponse> updateProduct(
            @PathVariable int id,
            @Valid @RequestPart("productDTORequest") ProductDTORequest updateProductDTORequest,
            @RequestPart("image") MultipartFile newImage){
       ProductDTOResponse updatedProductDTOResponse = productService.updatedProduct(id, updateProductDTORequest, newImage);
       return ResponseEntity.ok(updatedProductDTOResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable int id){
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/filter")
    public ResponseEntity<List<ProductDTOResponse>> filterProducts(
            @RequestParam(required = false) String categoryName,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice) {
        List<ProductDTOResponse> filteredProducts = productService.filterProducts(categoryName, minPrice, maxPrice);
        return ResponseEntity.ok(filteredProducts);
    }
}