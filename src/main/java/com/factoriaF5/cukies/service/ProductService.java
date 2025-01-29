package com.factoriaF5.cukies.service;

import com.factoriaF5.cukies.exception.category.CategoryNotFoundException;
import com.factoriaF5.cukies.exception.product.*;
import com.factoriaF5.cukies.DTOs.product.*;
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
    private final ProductMapper productMapper;

    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.productMapper = productMapper;
    }

    public List<ProductSummaryDTOResponse> getProducts(){
        List<Product> products = productRepository.findAll();
        if (products.isEmpty()) throw new ProductsNotFoundException();
        List<ProductSummaryDTOResponse> productSummaryDTOResponseList = products.stream()
                .map(product -> productMapper.toSummaryDTOResponse(product))
                .toList();
        return productSummaryDTOResponseList;
    }
    public ProductSummaryDTOResponse createProduct(ProductSummaryDTORequest productSummaryDTORequest){
        Product newProduct = productMapper.fromSummaryDTOToEntity(productSummaryDTORequest);
        Product savedProduct = productRepository.save(newProduct);
        return productMapper.toSummaryDTOResponse(savedProduct);
    }
    public Optional<ProductDetailDTOResponse> findProductById(int id){
        Optional<Product> foundProduct = productRepository.findById(id);
        if (foundProduct.isPresent()){
            ProductDetailDTOResponse productDetailDTOResponseById = productMapper.toDetailDTOResponse(foundProduct.get());
            return Optional.of(productDetailDTOResponseById);
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
    public ProductDetailDTOResponse updatedProduct(int id, ProductDetailDTORequest updateProductDetailDTORequest){
        Optional<Product> existingProduct = productRepository.findById(id);
        if (existingProduct.isPresent()){
            Product productToUpdate = existingProduct.get();
            productToUpdate.setName(updateProductDetailDTORequest.name());
            productToUpdate.setPrice(updateProductDetailDTORequest.price());
            productToUpdate.setImageUrl(updateProductDetailDTORequest.imageUrl());
            productToUpdate.setFeatured(updateProductDetailDTORequest.featured());

            if (updateProductDetailDTORequest.categoryId() != null) {
                Optional<Category> category = categoryRepository.findById(updateProductDetailDTORequest.categoryId());
                if (category.isPresent()) {
                    productToUpdate.setCategory(category.get());
                } else {
                    throw new CategoryNotFoundException("ID", updateProductDetailDTORequest.categoryId());
                }
            }
            Product updatedProduct = productRepository.save(productToUpdate);
            return productMapper.toDetailDTOResponse(updatedProduct);
        }
        throw new ProductNotFoundException("ID", id);
    }

    public List<ProductSummaryDTOResponse> getProductsByCategory (String categoryName){
        Category category = categoryRepository.findByName(categoryName)
                .orElseThrow(() -> new CategoryNotFoundException("name", categoryName));

        List<Product> productsByCategory = productRepository.findByCategory(category);

        return productsByCategory.stream()
                .map(productMapper::toSummaryDTOResponse)
                .toList();
    }

    public List<ProductSummaryDTOResponse> getProductsByPriceRange(double minPrice, double maxPrice) {
        if (minPrice > maxPrice) throw new InvalidPriceRangeException(minPrice, maxPrice);
        List<Product> productsByPrice = productRepository.findByPriceBetween(minPrice, maxPrice);
        return productsByPrice.stream()
                .map(product -> productMapper.toSummaryDTOResponse(product)).toList();
    }
    public List<Product> filterProducts(String categoryName, Double minPrice, Double maxPrice) {
        if (categoryName != null && !categoryName.isEmpty()) {
            return productRepository.findByCategoryNameAndPriceBetween(categoryName, minPrice, maxPrice);
        } else {
            return productRepository.findByPriceBetween(minPrice, maxPrice);
        }
    }


}