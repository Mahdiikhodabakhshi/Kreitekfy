package com.kreitek.Kreitekfy.song.application.services.impl;

import com.kreitek.Kreitekfy.category.domain.entity.Category;
import com.kreitek.Kreitekfy.category.domain.persistence.CategoryPersistence;
import com.kreitek.Kreitekfy.song.application.dto.SongDTO;
import com.kreitek.Kreitekfy.song.application.mapper.SongMapper;
import com.kreitek.Kreitekfy.song.application.services.SongService;
import com.kreitek.Kreitekfy.song.domain.entity.Song;
import com.kreitek.Kreitekfy.song.domain.repository.SongRepository;
import com.kreitek.Kreitekfy.userMusic.application.dto.UserMusicDto;
import com.kreitek.Kreitekfy.userMusic.application.service.UserMusicService;
import com.kreitek.Kreitekfy.userMusic.domain.persistence.UserMusicPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class SongServiceImpl implements SongService {

    private final SongRepository songRepository;
    private final SongMapper songMapper;
    private final UserMusicService userMusicService;
    private final UserMusicPersistence userMusicRepository;
    private final CategoryPersistence categoryPeersistence;

    @Autowired
    public SongServiceImpl(SongRepository songRepository, SongMapper songMapper, UserMusicService userMusicService, UserMusicPersistence userMusicRepository, CategoryPersistence categoryPeersistence) {
        this.songRepository = songRepository;
        this.songMapper = songMapper;
        this.userMusicService = userMusicService;
        this.userMusicRepository = userMusicRepository;
        this.categoryPeersistence = categoryPeersistence;
    }




    @Override
    public List<SongDTO> getSongs() {
        List<Song> songs = songRepository.findAll();
        return songMapper.toDto(songs);
    }

    @Override
    public List<SongDTO> getAllSongsByOrderByUploadDateDesc() {
//        List<Song> newestSong = this.addCalculatedValuesToSong(songRepository.findAll());
//        newestSong.sort(Comparator.comparing(Song::getUploadDate).reversed());
//        List<Song> fiveNewestSongs = new ArrayList<>();
//        for (int i = 0; i < newestSong.size(); i++){
//            fiveNewestSongs.add(newestSong.get(i));
//            if(i == 4) {
//                break;
//            }
//        }
//        return songMapper.toDto(fiveNewestSongs);
        List<Song> songs = songRepository.getAllSongsByOrderByUploadDateDesc();
        return songMapper.toDto(songs);
    }

    @Override
    public List<SongDTO> findByOrderByTotalRateDesc() {
        List<Song> calculatedAdded = this.addCalculatedValuesToSong(songRepository.findAll());


//        calculatedAdded.sort(Comparator.comparing(Song::getTotalRate).reversed());
//        List<Song> fiveBestRatedSongs = new ArrayList<>();
//        for (int i = 0; i < calculatedAdded.size(); i++){
//            fiveBestRatedSongs.add(calculatedAdded.get(i));
//            if(i == 4) {
//                break;
//            }
//        }

        return songMapper.toDto(calculatedAdded);//TODO QUERY
    }

    @Override
    public List<SongDTO> findByOrderByTotalViewsDesc() {

        List<Song> calculatedAdded = this.addCalculatedValuesToSong(songRepository.findAll());
        calculatedAdded.sort(Comparator.comparing(Song::getTotalViews).reversed());
        List<Song> fiveMostViewedSongs = new ArrayList<>();
        for (int i = 0; i < calculatedAdded.size(); i++){
            fiveMostViewedSongs.add(calculatedAdded.get(i));
            if(i == 4) {
                break;
            }
        }
        return songMapper.toDto(fiveMostViewedSongs); //TODO QUERY
    }

    @Override
    public List<SongDTO> findByCategoryOrderByTotalRateDesc(String categoryName) {
        return List.of(); //todo
    }

    @Override
    public List<SongDTO> findByOrderCategoryByTotalViewsDesc(String categoryName) {
        List<Song> calculatedAdded = this.addCalculatedValuesToSong(songRepository.findSongsByCategory(categoryName));
        calculatedAdded.sort(Comparator.comparing(Song::getTotalViews).reversed());
        List<Song> fiveMostViewedSongs = new ArrayList<>();
        for (int i = 0; i < calculatedAdded.size(); i++){
            fiveMostViewedSongs.add(calculatedAdded.get(i));
            if(i == 4) {
                break;
            }
        }
        return songMapper.toDto(fiveMostViewedSongs); //TODO QUERY
    }

    @Override
    public Page<SongDTO> getSongByCriteriaPaged(Pageable pageable, String filter) {
        Page<Song> songPage = songRepository.findAll(pageable, filter);
        return songPage.map(songMapper::toDto);
    }

    @Override
    public Optional<SongDTO> getSongById(Long songId) {
        Optional<Song> song = songRepository.findById(songId);
        if(song.isPresent()) {
            List<Song> songUodated = new ArrayList<>();
            songUodated.add(song.get());
            songUodated = this.addCalculatedValuesToSong(songUodated);
            song = Optional.of(songUodated.get(0));
        }
        return song.map(songMapper::toDto);
    }



    @Override
    public SongDTO saveSong(SongDTO songDTO) {
        songDTO.setTotalViews(0L);
        songDTO.setTotalRate(0D);
        songDTO.setUploadDate(new Date());
        Song song = songMapper.toEntity(songDTO);
        song = songRepository.save(song);
        return songMapper.toDto(song);
    }

    @Override
    public void deleteSong(Long songId) {
        songRepository.deleteById(songId);

    }


    @Override
    @Transactional
    public List<SongDTO> findByUserPreferences(String userId) {
        List<Category> categories = userMusicService.findUserMusicByUserEmail(userId);
        List<Long> categoryIds = new ArrayList<>();
        for (Category category : categories) {
            categoryIds.add(category.getId());
        }
        List<Song> songs = songRepository.findSongByUserPreference(categoryIds);

        return songMapper.toDto(songs);
    }

    @Override
    public List<SongDTO> getTop5SongsByCategory(String category) {
        List<Song> songByCategory = songRepository.findTop5SongByCategory(category);
        return songMapper.toDto(songByCategory);
    }

    @Override
    public List<Song> addCalculatedValuesToSong(List<Song> songList) {
        for (Song song : songList) {
            double totalRate = 0.0;
            long totalViews = 0L;
            int numRates = 0;

            List<UserMusicDto> userSongs = userMusicService.getAllUserSongBySongId(song.getId());
            for (UserMusicDto userMusicDto : userSongs) {
                totalViews += userMusicDto.getPersonalPlays();
                if (userMusicDto.getPersonalValorations() != null) {
                    totalRate += userMusicDto.getPersonalValorations();
                    numRates++;
                }
            }

            song.setTotalViews(totalViews);
            if (numRates != 0) {
                song.setTotalRate(totalRate / numRates);
            } else {
                song.setTotalRate(totalRate);
            }
        }
        return songList;
    }



}
