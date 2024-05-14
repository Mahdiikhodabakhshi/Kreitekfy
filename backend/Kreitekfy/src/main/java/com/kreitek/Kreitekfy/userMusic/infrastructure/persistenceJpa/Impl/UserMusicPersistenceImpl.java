package com.kreitek.Kreitekfy.userMusic.infrastructure.persistenceJpa.Impl;

import com.kreitek.Kreitekfy.category.domain.entity.Category;
import com.kreitek.Kreitekfy.userMusic.domain.entity.UserMusic;
import com.kreitek.Kreitekfy.userMusic.domain.persistence.UserMusicPersistence;
import com.kreitek.Kreitekfy.userMusic.infrastructure.persistenceJpa.UserMusicPersistenceJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public class UserMusicPersistenceImpl implements UserMusicPersistence {
    private final UserMusicPersistenceJpa userMusicPersistenceJpa;

    @Autowired
    public UserMusicPersistenceImpl(UserMusicPersistenceJpa userMusicPersistenceJpa) {
        this.userMusicPersistenceJpa = userMusicPersistenceJpa;
    }

    @Override
    public UserMusic saveUserMusic(UserMusic userSong) {
        return userMusicPersistenceJpa.save(userSong);
    }


    @Override
    public Optional<UserMusic> getUserMusicById(Long userMusicId) {
        return userMusicPersistenceJpa.findById(userMusicId);
    }

    @Override
    public List<Category> findSongByUserName(String userName) {
//        return userMusicPersistenceJpa.findByUser_username(userName);
        return userMusicPersistenceJpa.getMostListenedStylesByUser(userName);
    }


    @Override
    public List<UserMusic> getUserMusicBySongId(Long songId) {
        return userMusicPersistenceJpa.findBySong_id(songId);
    }

    @Override
    public Boolean existUserMusicBySongIdAndUserName(Long songId, String userName) {
        return userMusicPersistenceJpa.existsBySong_idAndUser_username(songId, userName);
    }

    @Override
    public Optional<UserMusic> findUserMusicBySongIdAndUserName(Long songId, String userName) {
        return userMusicPersistenceJpa.findBySong_idAndUser_username(songId, userName);
    }
}
