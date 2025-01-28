package com.factoriaF5.cukies.controller;

import com.factoriaF5.cukies.DTOs.product.ProductDTORequest;
import com.factoriaF5.cukies.DTOs.product.ProductDTOResponse;
import com.factoriaF5.cukies.exception.category.CategoryNotFoundException;
import com.factoriaF5.cukies.exception.product.ProductNotFoundException;
import com.factoriaF5.cukies.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        return new ResponseEntity<>(productService.getProducts(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTOResponse> getProductById(@PathVariable int id){
        return productService.findProductById(id)
                .map(product -> new ResponseEntity<>(product, HttpStatus.OK))
                .orElseThrow(() -> new ProductNotFoundException("ID", id));
    }

    @PostMapping
    public ResponseEntity<?> createProduct(@Valid @RequestBody ProductDTORequest productDTORequest) {
        //Hay que buscar si existen categorías y añadir listas categorías
        ProductDTOResponse newProductDTOResponse = productService.createProduct(productDTORequest);
        return new ResponseEntity<>(newProductDTOResponse, HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<ProductDTOResponse> updateProduct(
            @PathVariable int id,
            @Valid @RequestBody ProductDTORequest updateProductDTORequest){
        try {
            ProductDTOResponse updatedProductDTOResponse = productService.updatedProduct(id, updateProductDTORequest);
            return new ResponseEntity<>(updatedProductDTOResponse, HttpStatus.OK);
        } catch (ProductNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (CategoryNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable int id){
        Optional<ProductDTOResponse> productOptionalDTO = productService.findProductById(id);
        if (productOptionalDTO.isPresent()){
            productService.deleteProduct(id);
            String message = "Product " + productOptionalDTO.get().name() + " has been deleted.";
            return new ResponseEntity<>(message, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
    }
    @GetMapping("/filter")
    public ResponseEntity<List<ProductDTOResponse>> getProductsByCategory(@RequestParam String categoryName){
        List<ProductDTOResponse> products = productService.getProductsByCategory(categoryName);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
    @GetMapping("/filter/price")
    public ResponseEntity<List<ProductDTOResponse>> getProductsByPriceRange(
            @RequestParam double minPrice,
            @RequestParam double maxPrice) {
        List<ProductDTOResponse> products = productService.getProductsByPriceRange(minPrice, maxPrice);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

}