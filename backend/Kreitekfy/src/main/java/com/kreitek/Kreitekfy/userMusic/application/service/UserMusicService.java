package com.kreitek.Kreitekfy.userMusic.application.service;

import com.kreitek.Kreitekfy.category.domain.entity.Category;
import com.kreitek.Kreitekfy.userMusic.application.dto.UserMusicDto;

import java.util.List;

import java.util.Optional;

public interface UserMusicService {


    UserMusicDto saveUserMusic(UserMusicDto userMusicDto);
    UserMusicDto updateUserMusicPlaying(Long songId,String userId);
    UserMusicDto updateUserMusicRating(Long songId,String userId,Long rating);
    Optional<UserMusicDto> getUserMusicById(Long userMusicId);
    Boolean existUserMusicBySongIdAndUserId(Long songId, String userEmail);
    UserMusicDto findUserMusicBySongIdAndUserName(Long songId, String userEmail);
    List<Category> findUserMusicByUserEmail(String userEmail);
    List<UserMusicDto> getAllUserSongBySongId(Long songId);
    List<UserMusicDto> getAllUserSongByUserId(String userId);
}

