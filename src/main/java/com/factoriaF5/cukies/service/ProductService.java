package com.factoriaF5.cukies.service;

import com.factoriaF5.cukies.exception.category.CategoryNotFoundException;
import com.factoriaF5.cukies.exception.product.*;
import com.factoriaF5.cukies.DTOs.product.*;
import com.factoriaF5.cukies.model.Product;
import com.factoriaF5.cukies.model.Category;
import com.factoriaF5.cukies.repository.CategoryRepository;
import com.factoriaF5.cukies.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductMapper productMapper;

    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.productMapper = productMapper;
    }

    public List<ProductDTOResponse> getProducts(){
        List<Product> products = productRepository.findAll();
        if (products.isEmpty()) throw new ProductsNotFoundException();
        List<ProductDTOResponse> productSummaryDTOResponseList = products.stream()
                .map(product -> productMapper.toDTOResponse(product))
                .toList();
        return productSummaryDTOResponseList;
    }

    @Transactional
    public ProductDTOResponse createProduct(ProductDTORequest productDTORequest){
        Category category = categoryRepository.findById(productDTORequest.categoryId())
                .orElseThrow(() -> new CategoryNotFoundException("ID", productDTORequest.categoryId()));

        Product newProduct = productMapper.toEntity(productDTORequest);
        newProduct.setCategory(category);

        Product savedProduct = productRepository.save(newProduct);
        return productMapper.toDTOResponse(savedProduct);
    }
    public Optional<ProductDTOResponse> findProductById(int id){
        Optional<Product> foundProduct = productRepository.findById(id);
        if (foundProduct.isPresent()){
            ProductDTOResponse productDTOResponseById = productMapper.toDTOResponse(foundProduct.get());
            return Optional.of(productDTOResponseById);
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
    public ProductDTOResponse updatedProduct(int id, ProductDTORequest updateProductDTORequest){
        Optional<Product> existingProduct = productRepository.findById(id);
        if (existingProduct.isPresent()){
            Product productToUpdate = existingProduct.get();
            productToUpdate.setName(updateProductDTORequest.name());
            productToUpdate.setPrice(updateProductDTORequest.price());
            productToUpdate.setDescription(updateProductDTORequest.description());
            productToUpdate.setImageUrl(updateProductDTORequest.imageUrl());
            productToUpdate.setFeatured(updateProductDTORequest.featured());

            if (updateProductDTORequest.categoryId() != null) {
                Optional<Category> category = categoryRepository.findById(updateProductDTORequest.categoryId());
                if (category.isPresent()) {
                    productToUpdate.setCategory(category.get());
                } else {
                    throw new CategoryNotFoundException("ID", updateProductDTORequest.categoryId());
                }
            }
            Product updatedProduct = productRepository.save(productToUpdate);
            return productMapper.toDTOResponse(updatedProduct);
        }
        throw new ProductNotFoundException("ID", id);
    }

    public List<ProductDTOResponse> getProductsByCategory (String categoryName){
        Category category = categoryRepository.findByName(categoryName)
                .orElseThrow(() -> new CategoryNotFoundException("name", categoryName));

        List<Product> productsByCategory = productRepository.findByCategory(category);

        return productsByCategory.stream()
                .map(productMapper::toDTOResponse)
                .toList();
    }

    public List<ProductDTOResponse> getProductsByPriceRange(double minPrice, double maxPrice) {
        if (minPrice > maxPrice) throw new InvalidPriceRangeException(minPrice, maxPrice);
        List<Product> productsByPrice = productRepository.findByPriceBetween(minPrice, maxPrice);
        return productsByPrice.stream()
                .map(product -> productMapper.toDTOResponse(product)).toList();
    }
    public List<Product> filterProducts(String categoryName, Double minPrice, Double maxPrice) {
        if (categoryName != null && !categoryName.isEmpty()) {
            return productRepository.findByCategoryNameAndPriceBetween(categoryName, minPrice, maxPrice);
        } else {
            return productRepository.findByPriceBetween(minPrice, maxPrice);
        }
    }


}