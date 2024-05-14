package com.kreitek.Kreitekfy.album.domain.persistence;

import com.kreitek.Kreitekfy.album.domain.entity.Album;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;


public interface AlbumPersistence {
    List<Album> findAll();
    Page<Album> findAll(Pageable pageable , String search);
    Optional<Album> findById(long id);
    Album save(Album album);
    void delete(Long albumId);

    //todo get album by name

}
