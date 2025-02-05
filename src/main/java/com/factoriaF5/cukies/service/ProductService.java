package com.factoriaF5.cukies.service;

import com.factoriaF5.cukies.DTOs.image.ImageDTO;
import com.factoriaF5.cukies.exception.category.CategoryNotFoundException;
import com.factoriaF5.cukies.exception.image.ImageUploadException;
import com.factoriaF5.cukies.exception.product.*;
import com.factoriaF5.cukies.DTOs.product.*;
import com.factoriaF5.cukies.model.Image;
import com.factoriaF5.cukies.model.Product;
import com.factoriaF5.cukies.model.Category;
import com.factoriaF5.cukies.repository.CategoryRepository;
import com.factoriaF5.cukies.repository.ImageRepository;
import com.factoriaF5.cukies.repository.ProductRepository;
import com.factoriaF5.cukies.service.cloudinary.CloudinaryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private static final String CLOUDINARY_FOLDER = "cukies_shop";

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductMapper productMapper;
    private final CloudinaryService cloudinaryService;
    private final ImageRepository imageRepository;

    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository, ProductMapper productMapper, CloudinaryService cloudinaryService, ImageRepository imageRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.productMapper = productMapper;
        this.cloudinaryService = cloudinaryService;
        this.imageRepository = imageRepository;
    }

    public List<ProductDTOResponse> getProducts(){
        return Optional.of(productRepository.findAll())
                .filter(products -> !products.isEmpty())
                .map(products -> products.stream()
                        .map(productMapper::toDTOResponse)
                        .toList())
                .orElseThrow(ProductsNotFoundException::new);
    }

    @Transactional
    public ProductDTOResponse createProduct(ProductDTORequest productDTORequest, MultipartFile productFile) {

        Category category = categoryRepository.findById(productDTORequest.categoryId())
                .orElseThrow(() -> new CategoryNotFoundException("ID", productDTORequest.categoryId()));

        Product newProduct = productMapper.toEntity(productDTORequest);
        newProduct.setCategory(category);

        try {
            handleImageUploads(productFile, newProduct);
        } catch (ImageUploadException e) {
            throw e;
        }

        Product savedProduct = productRepository.save(newProduct);

        return productMapper.toDTOResponse(savedProduct);
    }

    public ProductDTOResponse findProductById(int id){
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("ID", id));
        return productMapper.toDTOResponse(product);
    }

    public void deleteProduct(int id) {
        productRepository.findById(id)
                .ifPresentOrElse(product -> productRepository.deleteById(id),
                        () -> { throw new ProductNotFoundException("ID", id); });
    }

    @Transactional
    public ProductDTOResponse updatedProduct(int id, ProductDTORequest updateProductDTORequest, MultipartFile image) {
        //producto a actualizar
        Product productToUpdate = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("ID", id));

        //actualizar datos de producto
        productToUpdate.setName(updateProductDTORequest.name());
        productToUpdate.setPrice(updateProductDTORequest.price());
        productToUpdate.setDescription(updateProductDTORequest.description());
        productToUpdate.setFeatured(updateProductDTORequest.featured());

        //si se ha cargado nueva imagen, gestionar la subida
        handleImageUploads(image, productToUpdate);

        //actualizar el estado del producto
        productToUpdate.setFeatured(updateProductDTORequest.featured());

        //actualizar categoria si se proporciona
        if (updateProductDTORequest.categoryId() != null) {
            Category category = categoryRepository.findById(updateProductDTORequest.categoryId())
                    .orElseThrow(() -> new CategoryNotFoundException("ID", updateProductDTORequest.categoryId()));
            productToUpdate.setCategory(category);
        }

        //guardar los cambios del producto
        Product updatedProduct = productRepository.save(productToUpdate);
        return productMapper.toDTOResponse(updatedProduct);
    }

    public List<ProductDTOResponse> filterProducts(String categoryName, Double minPrice, Double maxPrice) {
        return productRepository
                .findByCategoryNameIgnoreCaseAndPriceBetween(
                        categoryName != null && !categoryName.isEmpty() ? categoryName : "",
                        minPrice, maxPrice
                )
                .stream()
                .map(productMapper::toDTOResponse)
                .toList();
    }

    private Image handleImageUploads(MultipartFile imageFile, Product product) {
        if (imageFile == null || imageFile.isEmpty()) {
            return null;
        }

        String imageUrl = cloudinaryService.uploadImage(imageFile, CLOUDINARY_FOLDER);
        String imageName = "image-" + cleanProductName(imageFile.getName());

        Image image = new Image(imageName, imageUrl);
        image.setProduct(product);

        return imageRepository.save(image);
    }

    private String cleanProductName(String productName) {
        if (productName == null) return "";
        return productName.replace("[^a-zA-Z0-9_]", "_");
    }


}