package com.kreitek.Kreitekfy.category.application.mapper;

import com.kreitek.Kreitekfy.category.application.dto.CategoryDto;
import com.kreitek.Kreitekfy.category.domain.entity.Category;
import com.kreitek.Kreitekfy.genMapper.EntityMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper extends EntityMapper<CategoryDto,Category> {

    default Category fromId(Long id) {
        if (id == null) return null;
        Category category = new Category();
        category.setId(id);
        return category;
    }
}
