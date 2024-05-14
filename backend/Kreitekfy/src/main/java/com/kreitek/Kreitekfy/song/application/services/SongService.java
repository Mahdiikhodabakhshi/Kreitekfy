package com.kreitek.Kreitekfy.song.application.services;


import com.kreitek.Kreitekfy.song.application.dto.SongDTO;
import com.kreitek.Kreitekfy.song.domain.entity.Song;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface SongService {


    List<SongDTO> getSongs();
    List<SongDTO> getAllSongsByOrderByUploadDateDesc();
    List<SongDTO> findByOrderByTotalRateDesc();
    List<SongDTO> findByOrderByTotalViewsDesc();
    List<SongDTO> findByCategoryOrderByTotalRateDesc(String categoryName);
    List<SongDTO> findByOrderCategoryByTotalViewsDesc(String categoryName);
    Page<SongDTO> getSongByCriteriaPaged(Pageable pageable, String filter);
    Optional<SongDTO> getSongById(Long songId);
    List<Song> addCalculatedValuesToSong(List<Song> songList);
    SongDTO saveSong(SongDTO songDTO);
    void deleteSong(Long songId);
    List<SongDTO> findByUserPreferences(String userId);
    List<SongDTO> getTop5SongsByCategory(String category);

}
