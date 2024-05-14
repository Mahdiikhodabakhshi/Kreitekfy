package com.kreitek.Kreitekfy.artist.infraestructure.specs;


import com.kreitek.Kreitekfy.artist.domain.entity.Artist;
import com.kreitek.Kreitekfy.shared.specs.EntitySpecification;
import com.kreitek.Kreitekfy.shared.specs.SearchCriteria;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public class ArtistSpecification extends EntitySpecification<Artist> implements Specification<Artist> {

    public ArtistSpecification(List<SearchCriteria> criteria) {
        this.criteria = criteria;
    }

}
