package com.kreitek.Kreitekfy.category.infrastructure.persistenceJpa.Impl;

import com.kreitek.Kreitekfy.category.domain.entity.Category;
import com.kreitek.Kreitekfy.category.domain.persistence.CategoryPersistence;
import com.kreitek.Kreitekfy.category.infrastructure.persistenceJpa.CategoryRepositoryJpa;
import com.kreitek.Kreitekfy.category.infrastructure.specs.CategorySpecification;
import com.kreitek.Kreitekfy.shared.specs.SearchCriteriaHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CategoryPersistenceImpl implements CategoryPersistence {
    private final CategoryRepositoryJpa categoryRepositoryJpa;

    @Autowired
    public CategoryPersistenceImpl(CategoryRepositoryJpa categoryRepositoryJpa) {
        this.categoryRepositoryJpa = categoryRepositoryJpa;
    }

    @Override
    public Page<Category> getCategoriesByCriteriaPaged(Pageable pageable, String filter) {
        CategorySpecification specification =
                new CategorySpecification(SearchCriteriaHelper.fromFilterString(filter));
        return categoryRepositoryJpa.findAll(specification, pageable);
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepositoryJpa.findAll();
    }

    @Override
    public Optional<Category> getCategoryById(Long categoryId) {
        return categoryRepositoryJpa.findById(categoryId);
    }

    @Override
    public Category saveCategory(Category category) {
        return categoryRepositoryJpa.save(category);
    }

    @Override
    public void deleteCategory(Long categoryId) {
        categoryRepositoryJpa.deleteById(categoryId);
    }
}
