package com.factoriaF5.cukies.service;

import com.factoriaF5.cukies.DTOs.product.ProductDTO;
import com.factoriaF5.cukies.DTOs.product.ProductMapper;
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

    public List<ProductDTO> getProducts(){
        List<Product> products = productRepository.findAll();
        if (products.isEmpty()) throw new RuntimeException();
        List<ProductDTO> productDTOList = products.stream().map(product -> ProductMapper.entityToDTO(product)).toList();
        return productDTOList;
    }
    public ProductDTO createProduct(ProductDTO productDTO){
        Product newProduct = ProductMapper.dtoToEntity(productDTO);
        Product savedProduct = productRepository.save(newProduct);
        return ProductMapper.entityToDTO(savedProduct);
    }
    public Optional<ProductDTO> findProductById(int id){
        Optional<Product> foundProduct = productRepository.findById(id);
        if (foundProduct.isPresent()){
            ProductDTO productDTOById = ProductMapper.entityToDTO(foundProduct.get());
            return Optional.of(productDTOById);
        }
        throw new ObjectNotFoundException("Product", id);
    }
    public void deleteProduct(int id){
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()){
            productRepository.deleteById(id);
        }

    }
    public ProductDTO updatedProduct(int id, ProductDTO updateProductDTO){
        Optional<Product> existingProduct = productRepository.findById(id);
        if (existingProduct.isPresent()){
            Product productToUpdate = existingProduct.get();
            productToUpdate.setName(updateProductDTO.name());
            productToUpdate.setPrice(updateProductDTO.price());
            productToUpdate.setImageUrl(updateProductDTO.imageUrl());
            productToUpdate.setFeatured(updateProductDTO.featured());
            Product updatedProduct = productRepository.save(productToUpdate);
            return ProductMapper.entityToDTO(updatedProduct);
        }
        throw new ObjectNotFoundException("Product", id);
    }
}