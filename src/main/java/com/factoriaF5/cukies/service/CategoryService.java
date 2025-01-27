package com.factoriaF5.cukies.service;

import com.factoriaF5.cukies.DTOs.category.CategoryDTORequest;
import com.factoriaF5.cukies.DTOs.category.CategoryDTOResponse;
import com.factoriaF5.cukies.DTOs.category.CategoryMapper;
import com.factoriaF5.cukies.exception.category.CategoriesNotFoundException;
import com.factoriaF5.cukies.exception.category.CategoryNotFoundException;
import com.factoriaF5.cukies.model.Category;
import com.factoriaF5.cukies.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
    public CategoryDTOResponse createCategory(CategoryDTORequest categoryDTORequest){
        Category newCategory = CategoryMapper.dtoToEntity(categoryDTORequest);
        Category savedCategory = categoryRepository.save(newCategory);
        return CategoryMapper.entityToDTO(savedCategory);
    }
    public List<CategoryDTOResponse> getCategories(){
        List<Category> categories = categoryRepository.findAll();
        if (categories.isEmpty()) throw new CategoriesNotFoundException();
        List<CategoryDTOResponse> listCategories = categories.stream().map(category -> CategoryMapper.entityToDTO(category)).toList();
        return listCategories;
    }
    public Optional<CategoryDTOResponse> findCategoryById(int id){
        Optional<Category> foundCategory = categoryRepository.findById(id);
        if (foundCategory.isPresent()){
            CategoryDTOResponse categoryDTOById = CategoryMapper.entityToDTO(foundCategory.get());
            return Optional.of(categoryDTOById);
        }
        throw new CategoryNotFoundException("ID", id);
    }
    public void deleteCategory(int id){
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("ID", id));
        categoryRepository.delete(category);
    }
    public CategoryDTOResponse updateCategory(int id, CategoryDTORequest categoryDTORequest){
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("ID", id));
        category.setName(categoryDTORequest.name());

        Category updatedCategory = categoryRepository.save(category);
        return CategoryMapper.entityToDTO(updatedCategory);
    }
}
