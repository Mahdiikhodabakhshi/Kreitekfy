package com.kreitek.Kreitekfy.artist.application.service.impl;


import com.kreitek.Kreitekfy.artist.application.dto.ArtistDTO;
import com.kreitek.Kreitekfy.artist.application.mapper.ArtistMapper;
import com.kreitek.Kreitekfy.artist.application.service.ArtistService;
import com.kreitek.Kreitekfy.artist.domain.entity.Artist;
import com.kreitek.Kreitekfy.artist.domain.persistence.ArtistPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ArtistServiceImpl implements ArtistService {

    private final ArtistPersistence artistPersistence;
    private final ArtistMapper artistMapper;

    @Autowired
    public ArtistServiceImpl(ArtistPersistence artistPersistence, ArtistMapper artistMapper) {
        this.artistPersistence = artistPersistence;
        this.artistMapper = artistMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ArtistDTO> getArtistByCriteriaPaged(Pageable pageable, String filter) {
        Page<Artist> itemPage = this.artistPersistence.findAll(pageable,filter);
        return itemPage.map(artistMapper::toDto);
    }

    @Override
    public List<ArtistDTO> getArtists() {
        List<Artist> artist = this.artistPersistence.findAll();
        return this.artistMapper.toDto(artist);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ArtistDTO> getArtistById(Long artistId) {
        return this.artistPersistence
                .getArtistById(artistId)
                .map(artistMapper::toDto);
    }

    @Override
    @Transactional
    public ArtistDTO saveArtist(ArtistDTO artistDTO) {
        Artist artist = this.artistPersistence.saveArtist(this.artistMapper.toEntity(artistDTO));
        return this.artistMapper.toDto(artist);
    }

    @Override
    @Transactional
    public void deleteArtist(Long artistId) {
        this.artistPersistence.deleteArtist(artistId);

    }


}
