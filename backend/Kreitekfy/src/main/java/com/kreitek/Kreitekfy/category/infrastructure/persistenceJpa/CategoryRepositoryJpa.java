package com.kreitek.Kreitekfy.category.infrastructure.persistenceJpa;

import com.kreitek.Kreitekfy.category.domain.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CategoryRepositoryJpa extends JpaRepository<Category, Long>  , JpaSpecificationExecutor<Category> {
}
