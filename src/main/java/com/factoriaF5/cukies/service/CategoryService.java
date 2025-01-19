package com.factoriaF5.cukies.service;

import com.factoriaF5.cukies.DTOs.category.CategoryDTO;
import com.factoriaF5.cukies.DTOs.category.CategoryMapper;
import com.factoriaF5.cukies.exception.EmptyException;
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
    public CategoryDTO createCategory(CategoryDTO categoryDTO){
        Category newCategory = CategoryMapper.dtoToEntity(categoryDTO);
        Category savedCategory = categoryRepository.save(newCategory);
        return CategoryMapper.entityToDTO(savedCategory);
    }
    public List<CategoryDTO> getCategories(){
        List<Category> categories = categoryRepository.findAll();
        if (categories.isEmpty()) throw new EmptyException();
        List<CategoryDTO> listCategories = categories.stream().map(category -> CategoryMapper.entityToDTO(category)).toList();
        return listCategories;
    }
    public Optional<CategoryDTO> findCategoryById(int id){
        Optional<Category> foundCategory = categoryRepository.findById(id);
        if (foundCategory.isPresent()){
            CategoryDTO categoryDTOById = CategoryMapper.entityToDTO(foundCategory.get());
            return Optional.of(categoryDTOById);
        }
        throw new EmptyException();
    }
    public void deleteCategory(int id){
        Optional<Category> category = categoryRepository.findById(id);
        if (category.isPresent()){
            categoryRepository.delete(category.get());
        }
        throw new EmptyException();
    }
    public CategoryDTO updateCategory(int id, CategoryDTO categoryDTO){
        Optional<Category> foundCategory = categoryRepository.findById(id);
        if (foundCategory.isPresent()){
            Category categoryToUpdate = foundCategory.get();
            categoryToUpdate.setName(categoryDTO.name());
            Category updatedCategory = categoryRepository.save(categoryToUpdate);
            return  CategoryMapper.entityToDTO(updatedCategory);
        }
        //comprobar excepción personalizada
        throw new EmptyException();
    }
}
