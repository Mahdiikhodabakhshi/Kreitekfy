package com.kreitek.Kreitekfy.album.application.service;

import com.kreitek.Kreitekfy.album.application.dto.AlbumDto;
import com.kreitek.Kreitekfy.album.application.dto.AlbumSimpleDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;


public interface AlbumService {
    List<AlbumSimpleDto> getAlbums();
    Page<AlbumDto> getAlbumsByCriteriaPaged(Pageable pageable, String filter);
    Optional<AlbumDto> getAlbumById(Long albumId);
    AlbumDto saveAlbum(AlbumDto albumDto);
    void deleteAlbum(Long albumId);

}
