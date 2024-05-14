package com.kreitek.Kreitekfy.artist.infraestructure.persistence;


import com.kreitek.Kreitekfy.artist.domain.entity.Artist;
import com.kreitek.Kreitekfy.artist.domain.persistence.ArtistPersistence;
import com.kreitek.Kreitekfy.artist.infraestructure.specs.ArtistSpecification;
import com.kreitek.Kreitekfy.shared.specs.SearchCriteriaHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ArtistPersistenceImpl implements ArtistPersistence {

    private final ArtistRepository artistRepository;

    @Autowired
    public ArtistPersistenceImpl(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }


    @Override
    public Page<Artist> findAll(Pageable pageable, String filters) {

        ArtistSpecification specification = new ArtistSpecification(SearchCriteriaHelper.fromFilterString(filters));
        return this.artistRepository.findAll(specification,pageable);
    }

    @Override
    public List<Artist> findAll() {
        return artistRepository.findAll();
    }

    @Override
    public Optional<Artist> getArtistById(Long artistId) {
        return this.artistRepository.findById(artistId);
    }

    @Override
    public Artist saveArtist(Artist artist) {
        return this.artistRepository.save(artist);
    }

    @Override
    public void deleteArtist(Long artistId) {
        this.artistRepository.deleteById(artistId);
    }


}
