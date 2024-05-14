package com.kreitek.Kreitekfy.song.infrastructure.repository;


import com.kreitek.Kreitekfy.shared.specs.SearchCriteriaHelper;
import com.kreitek.Kreitekfy.song.domain.entity.Song;
import com.kreitek.Kreitekfy.song.domain.repository.SongRepository;
import com.kreitek.Kreitekfy.song.infrastructure.specs.SongSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class SongRepositoryImpl implements SongRepository {

    private final SongJpaRepository songJpaRepository;

    @Autowired
    public SongRepositoryImpl(SongJpaRepository songJpaRepository) {
        this.songJpaRepository = songJpaRepository;
    }

    @Override
    public List<Song> findAll() {
        return songJpaRepository.findAll();
    }



    @Override
    public Page<Song> findAll(Pageable pageable, String filters) {
        SongSpecification specification = new SongSpecification(SearchCriteriaHelper.fromFilterString(filters));
        return this.songJpaRepository.findAll(specification, pageable);
    }

    @Override
    public Song save(Song song) {
        return this.songJpaRepository.save(song);
    }

    @Override
    public void deleteById(Long songId) {
        this.songJpaRepository.deleteById(songId);
    }

    @Override
    public Optional<Song> findById(Long songId) {
        return songJpaRepository.findById(songId);
    }


    @Override
    public List<Song> getAllSongsByOrderByUploadDateDesc() {
        return songJpaRepository.getAllSongsByOrderByUploadDateDesc();
    }

    @Override
    public List<Song> getTopSongsByOrderByTotalViewDesc() {
        return List.of();
    }

    @Override
    public List<Song> findTop5SongByCategory(String category) {
//        return songJpaRepository.findAllByCategory_Name(category);
        return songJpaRepository.findTop5ByCategory_NameOrderByUploadDateDesc(category);
    }

    @Override
    public List<Song> findSongsByCategory(String category) {
        return songJpaRepository.findAllByCategory_Name(category);
    }


    @Override
    public List<Song> findSongByUserPreference(List<Long> categoryIds) {

        return songJpaRepository.findUserRecommendation(categoryIds);
    }
}


