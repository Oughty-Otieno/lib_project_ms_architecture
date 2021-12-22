package com.libproject.sc.service;

import com.libproject.sc.domain.Category;
import com.libproject.sc.repository.CategoryRepository;

import com.libproject.sc.service.dto.CategoryDTO;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public CategoryDTO getOneCategory(Long id){

        Category category = categoryRepository.findById(id).orElse(null);
        CategoryDTO currentCategoryDTO = new CategoryDTO(category);
        currentCategoryDTO.setAvailable_number(190);
        currentCategoryDTO.setTotal_number(200);
        return currentCategoryDTO;
    }
}
