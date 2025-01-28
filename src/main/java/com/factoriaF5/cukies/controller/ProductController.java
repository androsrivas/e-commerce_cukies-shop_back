package com.factoriaF5.cukies.controller;

import com.factoriaF5.cukies.DTOs.product.ProductDetailDTORequest;
import com.factoriaF5.cukies.DTOs.product.ProductDetailDTOResponse;
import com.factoriaF5.cukies.DTOs.product.ProductSummaryDTORequest;
import com.factoriaF5.cukies.DTOs.product.ProductSummaryDTOResponse;
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
    public ResponseEntity<List<ProductSummaryDTOResponse>> getAll(){
        return new ResponseEntity<>(productService.getProducts(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDetailDTOResponse> getProductById(@PathVariable int id){
        return productService.findProductById(id)
                .map(product -> new ResponseEntity<>(product, HttpStatus.OK))
                .orElseThrow(() -> new ProductNotFoundException("ID", id));
    }

    @PostMapping
    public ResponseEntity<?> createProduct(@Valid @RequestBody ProductSummaryDTORequest productSummaryDTORequest) {
        //Hay que buscar si existen categorías y añadir listas categorías
        ProductSummaryDTOResponse newProductSummaryDTOResponse = productService.createProduct(productSummaryDTORequest);
        return new ResponseEntity<>(newProductSummaryDTOResponse, HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<ProductDetailDTOResponse> updateProduct(
            @PathVariable int id,
            @Valid @RequestBody ProductDetailDTORequest updateProductDetailDTORequest){
        try {
            ProductDetailDTOResponse updatedProductDetailDTOResponse = productService.updatedProduct(id, updateProductDetailDTORequest);
            return new ResponseEntity<>(updatedProductDetailDTOResponse, HttpStatus.OK);
        } catch (ProductNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (CategoryNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable int id){
        Optional<ProductDetailDTOResponse> productOptionalDTO = productService.findProductById(id);
        if (productOptionalDTO.isPresent()){
            productService.deleteProduct(id);
            String message = "Product " + productOptionalDTO.get().name() + " has been deleted.";
            return new ResponseEntity<>(message, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
    }
    @GetMapping("/filter")
    public ResponseEntity<List<ProductSummaryDTOResponse>> getProductsByCategory(@RequestParam String categoryName){
        List<ProductSummaryDTOResponse> products = productService.getProductsByCategory(categoryName);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
    @GetMapping("/filter/price")
    public ResponseEntity<List<ProductSummaryDTOResponse>> getProductsByPriceRange(
            @RequestParam double minPrice,
            @RequestParam double maxPrice) {
        List<ProductSummaryDTOResponse> products = productService.getProductsByPriceRange(minPrice, maxPrice);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

}