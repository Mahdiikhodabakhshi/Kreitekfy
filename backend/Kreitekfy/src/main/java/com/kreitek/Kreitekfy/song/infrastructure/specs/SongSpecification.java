package com.kreitek.Kreitekfy.song.infrastructure.specs;


import com.kreitek.Kreitekfy.shared.specs.EntitySpecification;
import com.kreitek.Kreitekfy.shared.specs.SearchCriteria;
import com.kreitek.Kreitekfy.shared.specs.SearchOperation;
import com.kreitek.Kreitekfy.song.domain.entity.Song;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class SongSpecification extends EntitySpecification<Song> implements Specification<Song>{
    public SongSpecification(List<SearchCriteria> criteria) {
        this.criteria = criteria;
    }

    @Override
    public Predicate toPredicate(Root<Song> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        List<Predicate> predicates = new ArrayList<>();

        for (SearchCriteria criteria : criteria) {
            if (criteria.getOperation().equals(SearchOperation.EQUAL)){
                if (criteria.getKey().equals("title")) {
                    predicates.add(builder.equal(root.get("title"), criteria.getValue()));
                }else if (criteria.getKey().equals("artists")) {
                    predicates.add(builder.equal(root.get("artists").get("name"), criteria.getValue()));
                }else if (criteria.getKey().equals("album")) {
                    predicates.add(builder.equal(root.get("album").get("title"), criteria.getValue()));
                }else if (criteria.getKey().equals("category")) {
                    predicates.add(builder.equal(root.get("category").get("name"), criteria.getValue()));
                }
            }else if (criteria.getOperation().equals(SearchOperation.MATCH)){

                if (criteria.getKey().equals("title")) {
                    predicates.add(builder.like(builder.lower(root.get("title")),"%"+ criteria.getValue().toString().toLowerCase()+"%"));
                }else if (criteria.getKey().equals("artists")) {
                    predicates.add(builder.like(builder.lower(root.get("artists").get("name")),"%"+ criteria.getValue().toString().toLowerCase()+"%"));
//                    predicates.add(builder.equal(root.get("artists").get("name"), criteria.getValue()));
                }else if (criteria.getKey().equals("album")) {
                    predicates.add(builder.like(builder.lower(root.get("album").get("title")),"%"+ criteria.getValue().toString().toLowerCase()+"%"));
//                    predicates.add(builder.equal(root.get("album").get("title"), criteria.getValue()));
                }else if (criteria.getKey().equals("category")) {
                    predicates.add(builder.like(builder.lower(root.get("category").get("name")),"%"+ criteria.getValue().toString().toLowerCase()+"%"));
//                    predicates.add(builder.equal(root.get("category").get("name"), criteria.getValue()));
                }

//                predicates.add(builder.like(
//                        builder.lower(root.get(criteria.getKey())),
//                        "%" + criteria.getValue().toString().toLowerCase() + "%"));
            }


        }
        return builder.and(predicates.toArray(new Predicate[0]));
    }
}
