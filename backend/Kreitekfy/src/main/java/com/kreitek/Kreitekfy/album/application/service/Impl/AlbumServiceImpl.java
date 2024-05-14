package com.kreitek.Kreitekfy.album.application.service.Impl;

import com.kreitek.Kreitekfy.album.application.dto.AlbumDto;
import com.kreitek.Kreitekfy.album.application.dto.AlbumSimpleDto;
import com.kreitek.Kreitekfy.album.application.mapper.AlbumMapper;
import com.kreitek.Kreitekfy.album.application.service.AlbumService;
import com.kreitek.Kreitekfy.album.domain.entity.Album;
import com.kreitek.Kreitekfy.album.domain.persistence.AlbumPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AlbumServiceImpl implements AlbumService {

    private final AlbumPersistence albumPersistence;
    private final AlbumMapper albumMapper;

    @Autowired
    public AlbumServiceImpl(AlbumPersistence albumPersistence, AlbumMapper albumMapper) {
        this.albumPersistence = albumPersistence;
        this.albumMapper = albumMapper;
    }


    @Override
    public List<AlbumSimpleDto> getAlbums() {
        List<Album> albums = albumPersistence.findAll();
        return albumMapper.toSimpleDto(albums);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<AlbumDto> getAlbumsByCriteriaPaged(Pageable pageable, String filter) {

        Page<Album> albumPage = albumPersistence.findAll(pageable, filter);
        return albumPage.map(albumMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<AlbumDto> getAlbumById(Long albumId) {
        return albumPersistence.findById(albumId).map(albumMapper::toDto);
    }

    @Override
    @Transactional
    public AlbumDto saveAlbum(AlbumDto albumDto) {
        Album album = albumMapper.toEntity(albumDto);
        album = albumPersistence.save(album);
        return albumMapper.toDto(album);
    }

    @Override
    public void deleteAlbum(Long albumId) {
        albumPersistence.delete(albumId);
    }
}
