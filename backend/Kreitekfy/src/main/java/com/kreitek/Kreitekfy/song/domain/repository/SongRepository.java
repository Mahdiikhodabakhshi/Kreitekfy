package com.kreitek.Kreitekfy.song.domain.repository;


import com.kreitek.Kreitekfy.song.domain.entity.Song;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;


public interface SongRepository {
    List<Song> findAll();
    Page<Song> findAll(Pageable pageable, String filters);
    Song save(Song entity);
    void deleteById(Long id);
    Optional<Song> findById(Long id);
    List<Song> getAllSongsByOrderByUploadDateDesc();
    List<Song> getTopSongsByOrderByTotalViewDesc();
    List<Song> findTop5SongByCategory(String category);
    List<Song> findSongsByCategory(String category);
    List<Song> findSongByUserPreference(List<Long> categoryIds);
}
