package com.kreitek.Kreitekfy.category.application.service;

import com.kreitek.Kreitekfy.category.application.dto.CategoryDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    Page<CategoryDto> getCategoriesByCriteriaPaged(Pageable pageable, String filter);
    List<CategoryDto> getAllCategories();
    Optional<CategoryDto> getCategoryById(Long categoryId);
    CategoryDto saveCategory(CategoryDto categoryDto);
    void deleteCategory(Long categoryId);
}
