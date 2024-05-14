package com.kreitek.Kreitekfy.album.infrastructure.persistenceJpa.Impl;

import com.kreitek.Kreitekfy.album.domain.entity.Album;
import com.kreitek.Kreitekfy.album.domain.persistence.AlbumPersistence;
import com.kreitek.Kreitekfy.album.infrastructure.persistenceJpa.AlbumRepositoryJpa;
import com.kreitek.Kreitekfy.album.infrastructure.specs.AlbumSpecification;
import com.kreitek.Kreitekfy.shared.specs.SearchCriteriaHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class AlbumPersistenceImpl implements AlbumPersistence {
    private final AlbumRepositoryJpa albumRepositoryJpa;
    @Autowired
    public AlbumPersistenceImpl(AlbumRepositoryJpa albumRepositoryJpa) {
        this.albumRepositoryJpa = albumRepositoryJpa;
    }

    @Override
    public List<Album> findAll() {
        return albumRepositoryJpa.findAll();
    }

    @Override
    public Page<Album> findAll(Pageable pageable, String search) {
        AlbumSpecification albumSpecification =
                new AlbumSpecification(SearchCriteriaHelper.fromFilterString(search));
        return albumRepositoryJpa.findAll(albumSpecification , pageable);
    }

    @Override
    public Optional<Album> findById(long id) {
        return albumRepositoryJpa.findById(id);
    }

    @Override
    public Album save(Album album) {

        return albumRepositoryJpa.save(album);
    }

    @Override
    public void delete(Long albumId) {
        albumRepositoryJpa.deleteById(albumId);
    }

}
