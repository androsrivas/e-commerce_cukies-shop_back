package com.factoriaF5.cukies.service;

import com.factoriaF5.cukies.DTOs.category.CategoryDTORequest;
import com.factoriaF5.cukies.DTOs.product.ProductDTO;
import com.factoriaF5.cukies.DTOs.product.ProductMapper;
import com.factoriaF5.cukies.exception.category.CategoryNotFoundException;
import com.factoriaF5.cukies.exception.product.InvalidPriceRangeException;
import com.factoriaF5.cukies.exception.product.ProductNotFoundException;
import com.factoriaF5.cukies.exception.product.ProductsNotFoundException;
import com.factoriaF5.cukies.model.Product;
import com.factoriaF5.cukies.model.Category;
import com.factoriaF5.cukies.repository.CategoryRepository;
import com.factoriaF5.cukies.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<ProductDTO> getProducts(){
        List<Product> products = productRepository.findAll();
        if (products.isEmpty()) throw new ProductsNotFoundException();
        List<ProductDTO> productDTOList = products.stream().map(product -> ProductMapper.entityToDTO(product)).toList();
        return productDTOList;
    }
    public ProductDTO createProduct(ProductDTO productDTO){
        Product newProduct = ProductMapper.dtoToEntity(productDTO, categoryRepository);
        Product savedProduct = productRepository.save(newProduct);
        return ProductMapper.entityToDTO(savedProduct);
    }
    public Optional<ProductDTO> findProductById(int id){
        Optional<Product> foundProduct = productRepository.findById(id);
        if (foundProduct.isPresent()){
            ProductDTO productDTOById = ProductMapper.entityToDTO(foundProduct.get());
            return Optional.of(productDTOById);
        }
        throw new ProductNotFoundException("ID", id);
    }
    public void deleteProduct(int id){
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()){
            productRepository.deleteById(id);
        } else {
            throw new ProductNotFoundException("ID", id);
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
        throw new ProductNotFoundException("ID", id);
    }

    public List<ProductDTO> getProductsByCategory (CategoryDTORequest categoryDTO){
        Optional<Category> categoryOptional = categoryRepository.findByName(categoryDTO.name());
        if (categoryOptional.isPresent()){
            List<Product> productsByCategory = productRepository.findByCategory(categoryOptional);
            return productsByCategory.stream()
                    .map(product -> ProductMapper.entityToDTO(product))
                    .toList();
        }

        throw new CategoryNotFoundException("name", categoryDTO.name());
    }
    public List<ProductDTO> getProductsByPriceRange(double minPrice, double maxPrice) {
        if (minPrice > maxPrice) throw new InvalidPriceRangeException(minPrice, maxPrice);
        List<Product> productsByPrice = productRepository.findByPriceBetween(minPrice, maxPrice);
        return productsByPrice.stream()
                .map(product -> ProductMapper.entityToDTO(product)).toList();
    }


}