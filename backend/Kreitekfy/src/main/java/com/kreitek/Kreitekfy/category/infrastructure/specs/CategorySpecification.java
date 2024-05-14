package com.kreitek.Kreitekfy.category.infrastructure.specs;



import com.kreitek.Kreitekfy.category.domain.entity.Category;
import com.kreitek.Kreitekfy.shared.specs.EntitySpecification;
import com.kreitek.Kreitekfy.shared.specs.SearchCriteria;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public class CategorySpecification extends EntitySpecification<Category> implements Specification<Category> {

    public CategorySpecification(List<SearchCriteria> criteria) {
        this.criteria = criteria;
    }

}