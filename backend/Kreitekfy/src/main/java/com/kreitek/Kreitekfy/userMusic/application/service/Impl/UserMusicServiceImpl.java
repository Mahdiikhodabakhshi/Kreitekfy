package com.kreitek.Kreitekfy.userMusic.application.service.Impl;

import com.kreitek.Kreitekfy.category.domain.entity.Category;
import com.kreitek.Kreitekfy.song.application.dto.SongDTO;
import com.kreitek.Kreitekfy.song.application.services.SongService;
import com.kreitek.Kreitekfy.song.domain.entity.Song;
import com.kreitek.Kreitekfy.user.application.dto.UserDto;
import com.kreitek.Kreitekfy.user.application.service.AuthService;
import com.kreitek.Kreitekfy.user.domain.entity.User;
import com.kreitek.Kreitekfy.userMusic.application.dto.UserMusicDto;
import com.kreitek.Kreitekfy.userMusic.application.mapper.UserMusicMapper;
import com.kreitek.Kreitekfy.userMusic.application.service.UserMusicService;
import com.kreitek.Kreitekfy.userMusic.domain.entity.UserMusic;
import com.kreitek.Kreitekfy.userMusic.domain.persistence.UserMusicPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserMusicServiceImpl implements UserMusicService {
    private final UserMusicPersistence userMusicPersistence;
    private final UserMusicMapper userMusicMapper;


    @Autowired
    public UserMusicServiceImpl(UserMusicPersistence userMusicPersistence, UserMusicMapper userMusicMapper) {
        this.userMusicPersistence = userMusicPersistence;
        this.userMusicMapper = userMusicMapper;

    }


    @Override
    @Transactional
    public UserMusicDto saveUserMusic(UserMusicDto userMusicDto) {
        userMusicDto.setPersonalPlays(1L);
        UserMusic userMusic = userMusicMapper.toEntity(userMusicDto);
        userMusic = userMusicPersistence.saveUserMusic(userMusic);
        return userMusicMapper.toDto(userMusic);
    }

    @Override
    @Transactional
    public UserMusicDto updateUserMusicPlaying(Long songId,String userId) {
        Optional<UserMusic> userMusic = userMusicPersistence.findUserMusicBySongIdAndUserName(songId,userId);
        if(userMusic.isPresent()) {
            UserMusic userMusic1 = userMusic.get();
            userMusic1.setPersonalPlays(userMusic1.getPersonalPlays() + 1);
            userMusic1 = userMusicPersistence.saveUserMusic(userMusic1);
            return userMusicMapper.toDto(userMusic1);
        }else {
            UserMusicDto userMusicDto = new UserMusicDto();
            userMusicDto.setUserId(userId);
            userMusicDto.setSongId(songId);
            userMusicDto.setPersonalPlays(1L);
            UserMusic userMusic1 = userMusicMapper.toEntity(userMusicDto);
            userMusic1 = userMusicPersistence.saveUserMusic(userMusic1);
            return userMusicMapper.toDto(userMusic1);

        }
    }


    @Override
    @Transactional
    public UserMusicDto updateUserMusicRating(Long songId,String userId,Long rating) {
        Optional<UserMusic> userMusic = userMusicPersistence.findUserMusicBySongIdAndUserName(songId,userId);
        if(userMusic.isPresent()) {
            UserMusic userMusic1 = userMusic.get();

            userMusic1.setPersonalValorations( rating);
            userMusic1 = userMusicPersistence.saveUserMusic(userMusic1);
            return userMusicMapper.toDto(userMusic1);
        }else {
            UserMusicDto userMusicDto = new UserMusicDto();
            userMusicDto.setUserId(userId);
            userMusicDto.setSongId(songId);
            userMusicDto.setPersonalPlays(0L);
            userMusicDto.setPersonalValorations(rating);
            UserMusic userMusic1 = userMusicMapper.toEntity(userMusicDto);

            userMusic1 = userMusicPersistence.saveUserMusic(userMusic1);
            return userMusicMapper.toDto(userMusic1);
        }
//        UserMusic userMusic = userMusicMapper.toEntity(userMusicDto);
//        userMusic = userMusicPersistence.saveUserMusic(userMusic);
//        return userMusicMapper.toDto(userMusic);
    }

    @Override
    public Optional<UserMusicDto> getUserMusicById(Long userMusicId) {
        return userMusicPersistence
                .getUserMusicById(userMusicId)
                .map(userMusicMapper::toDto);
    }

    @Override
    public Boolean existUserMusicBySongIdAndUserId(Long songId, String userName) {
        return userMusicPersistence.existUserMusicBySongIdAndUserName(songId, userName);
    }

    @Override
    public UserMusicDto findUserMusicBySongIdAndUserName(Long songId, String userName) {
        Optional<UserMusic> userMusic = userMusicPersistence.findUserMusicBySongIdAndUserName(songId, userName);
        return userMusic.map(userMusicMapper::toDto).orElse(null);
    }

    @Override
    public List<Category> findUserMusicByUserEmail(String userName) {
        List<Category> userMusicList = userMusicPersistence.findSongByUserName(userName);
        return userMusicList;
    }

    @Override
    public List<UserMusicDto> getAllUserSongBySongId(Long songId) {
        List<UserMusic> userMusicList = userMusicPersistence.getUserMusicBySongId(songId);
        return userMusicMapper.toDto(userMusicList);
    }

    @Override
    public List<UserMusicDto> getAllUserSongByUserId(String userId) {
        return List.of();
    }
}
