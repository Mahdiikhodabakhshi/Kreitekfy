package com.kreitek.Kreitekfy.category.application.service.Impl;

import com.kreitek.Kreitekfy.category.application.dto.CategoryDto;
import com.kreitek.Kreitekfy.category.application.mapper.CategoryMapper;
import com.kreitek.Kreitekfy.category.application.service.CategoryService;
import com.kreitek.Kreitekfy.category.domain.entity.Category;
import com.kreitek.Kreitekfy.category.domain.persistence.CategoryPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryPersistence categoryPersistence;
    private final CategoryMapper categoryMapper;

    @Autowired
    public CategoryServiceImpl(CategoryPersistence categoryPersistence, CategoryMapper categoryMapper) {
        this.categoryPersistence = categoryPersistence;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public Page<CategoryDto> getCategoriesByCriteriaPaged(Pageable pageable, String filter) {
        Page<Category> categories = categoryPersistence.getCategoriesByCriteriaPaged(pageable, filter);
        return categories.map(categoryMapper::toDto);
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        List<Category> categories = categoryPersistence.getAllCategories();
        return categoryMapper.toDto(categories);
    }

    @Override
    public Optional<CategoryDto> getCategoryById(Long categoryId) {
        return categoryPersistence.getCategoryById(categoryId)
                .map(categoryMapper::toDto);
    }

    @Override
    public CategoryDto saveCategory(CategoryDto categoryDto) {
        Category category = categoryMapper.toEntity(categoryDto);
        category = categoryPersistence.saveCategory(category);
        return categoryMapper.toDto(category);
    }

    @Override
    public void deleteCategory(Long categoryId) {
        categoryPersistence.deleteCategory(categoryId);
    }
}
