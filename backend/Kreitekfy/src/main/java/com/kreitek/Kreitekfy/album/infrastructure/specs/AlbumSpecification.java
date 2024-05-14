package com.kreitek.Kreitekfy.album.infrastructure.specs;


import com.kreitek.Kreitekfy.album.domain.entity.Album;
import com.kreitek.Kreitekfy.shared.specs.EntitySpecification;
import com.kreitek.Kreitekfy.shared.specs.SearchCriteria;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public class AlbumSpecification extends EntitySpecification<Album> implements Specification<Album> {

    public AlbumSpecification(List<SearchCriteria> criteria) {
        this.criteria = criteria;
    }

}