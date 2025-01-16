package com.factoriaF5.cukies.controller;

import com.factoriaF5.cukies.DTOs.ErrorDTO;
import com.factoriaF5.cukies.DTOs.product.ProductDTO;
import com.factoriaF5.cukies.DTOs.product.ProductMapper;
import com.factoriaF5.cukies.model.Product;
import com.factoriaF5.cukies.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAll(){
        List<ProductDTO> products = productService.getAllProducts().stream()
                .map(product -> ProductMapper.toproductDTO(product))
                .collect(Collectors.toList());
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO>getById(@PathVariable int id){
        Optional<Product> productOptional = productService.findProductById(id);
        if (productOptional.isPresent()){
            ProductDTO product = ProductMapper.toproductDTO(productOptional.get());
            return new ResponseEntity<>(product, HttpStatus.OK);
        }
        //Aquí no tendría que devolver una exception?
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<?> createProduct(@Valid @RequestBody ProductDTO productDTO){
        //Hay que buscar si existen categorías y añadir lista categorías
       try {
           Product newProduct = ProductMapper.toEntity(productDTO);
           Product savedProduct = productService.createProduct(newProduct);
           ProductDTO savedProductDTO = ProductMapper.toproductDTO(savedProduct);
           return new ResponseEntity<>(savedProductDTO, HttpStatus.CREATED);
       } catch (IllegalArgumentException e){
           ErrorDTO errorResponse = new ErrorDTO("BAD_REQUEST", e.getMessage());
           return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
       }
    }
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable int id, @RequestBody Product updateProduct){
        try {
            Product product = productService.updatedProduct(id, updateProduct);
            return new ResponseEntity<>(product, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable int id){
        Optional<Product> productOptional = productService.findProductById(id);
        if (productOptional.isPresent()){
            Product product = productOptional.get();
            productService.deleteProduct(id);
            String message = "Product " + product.getName() + " has been deleted.";
            return new ResponseEntity<>(message, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
    }

}
