package com.kreitek.Kreitekfy.category.domain.persistence;

import com.kreitek.Kreitekfy.category.domain.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface CategoryPersistence {
    Page<Category> getCategoriesByCriteriaPaged(Pageable pageable, String filter);
    List<Category> getAllCategories();
    Optional<Category> getCategoryById(Long categoryId);
    Category saveCategory(Category category);
    void deleteCategory(Long categoryId);

}
