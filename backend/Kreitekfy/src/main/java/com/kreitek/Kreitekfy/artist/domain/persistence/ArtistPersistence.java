package com.kreitek.Kreitekfy.artist.domain.persistence;


import com.kreitek.Kreitekfy.artist.domain.entity.Artist;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;


public interface ArtistPersistence {
    Page<Artist> findAll(Pageable pageable, String filters);
    List<Artist> findAll();
    Optional<Artist> getArtistById(Long artistId);
    Artist saveArtist(Artist artist);
    void deleteArtist(Long artistId);

}
