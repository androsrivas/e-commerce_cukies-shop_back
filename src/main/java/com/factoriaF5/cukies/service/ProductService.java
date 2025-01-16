package com.factoriaF5.cukies.service;

import com.factoriaF5.cukies.exception.ObjectNotFoundException;
import com.factoriaF5.cukies.model.Product;
import com.factoriaF5.cukies.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }
    public Product createProduct(Product newProduct){
        return productRepository.save(newProduct);
    }
    public Optional<Product> findProductById(int id){
        Optional<Product> foundProduct = productRepository.findById(id);
        if (foundProduct.isPresent()){
            return productRepository.findById(id);
        }
        throw new ObjectNotFoundException("Product", id);
    }
    public void deleteProduct(int id){
        productRepository.deleteById(id);
    }
    public Product updatedProduct(int id, Product updateProduct){
        Optional<Product> foundProduct = productRepository.findById(id);
        if (foundProduct.isPresent()){
            Product existingProduct = foundProduct.get();
            existingProduct.setName(updateProduct.getName());
            existingProduct.setPrice(updateProduct.getPrice());
            existingProduct.setImageUrl(updateProduct.getImageUrl());
            existingProduct.setFeatured(updateProduct.isFeatured());

            return productRepository.save(existingProduct);
        }
        throw new ObjectNotFoundException("Product", id);
    }
}
