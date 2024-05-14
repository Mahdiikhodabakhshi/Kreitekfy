package com.kreitek.Kreitekfy.userMusic.domain.persistence;

import com.kreitek.Kreitekfy.category.domain.entity.Category;
import com.kreitek.Kreitekfy.userMusic.domain.entity.UserMusic;

import java.util.List;
import java.util.Optional;

public interface UserMusicPersistence {
    UserMusic saveUserMusic(UserMusic userSong);
    Optional<UserMusic> getUserMusicById(Long userMusicId);
    List<Category> findSongByUserName(String userName);
    List<UserMusic> getUserMusicBySongId(Long songId);
    Boolean existUserMusicBySongIdAndUserName(Long songId, String userName);
    Optional<UserMusic> findUserMusicBySongIdAndUserName(Long songId, String userName);
}
