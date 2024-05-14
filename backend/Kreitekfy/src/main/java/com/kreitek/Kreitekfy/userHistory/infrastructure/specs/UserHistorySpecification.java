package com.kreitek.Kreitekfy.userHistory.infrastructure.specs;


import com.kreitek.Kreitekfy.shared.specs.EntitySpecification;
import com.kreitek.Kreitekfy.shared.specs.SearchCriteria;
import com.kreitek.Kreitekfy.shared.specs.SearchOperation;
import com.kreitek.Kreitekfy.song.domain.entity.Song;
import com.kreitek.Kreitekfy.userHistory.domain.entity.UserHistory;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class UserHistorySpecification extends EntitySpecification<UserHistory> implements Specification<UserHistory>{
    public UserHistorySpecification(List<SearchCriteria> criteria) {
        this.criteria = criteria;
    }

    @Override
    public Predicate toPredicate(Root<UserHistory> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        List<Predicate> predicates = new ArrayList<>();

        for (SearchCriteria criteria : criteria) {
            if (criteria.getOperation().equals(SearchOperation.EQUAL)){
                if (criteria.getKey().equals("user")) {
                    predicates.add(builder.equal(root.get("user").get("username"), criteria.getValue()));
                }
            }

        }
        return builder.and(predicates.toArray(new Predicate[0]));
    }
}
