package com.kreitek.Kreitekfy.userMusic.application.dto;


import java.io.Serializable;
import java.util.Objects;

public class UserMusicDto implements Serializable {
    private Long id;
    private String userId;
    private Long songId;
    private Long personalPlays;
    private Long personalValorations;

    public UserMusicDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Long getSongId() {
        return songId;
    }

    public void setSongId(Long songId) {
        this.songId = songId;
    }

    public Long getPersonalPlays() {
        return personalPlays;
    }

    public void setPersonalPlays(Long personalPlays) {
        this.personalPlays = personalPlays;
    }

    public Long getPersonalValorations() {
        return personalValorations;
    }

    public void setPersonalValorations(Long personalValorations) {
        this.personalValorations = personalValorations;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserMusicDto that = (UserMusicDto) o;
        return Objects.equals(id, that.id) && Objects.equals(userId, that.userId) && Objects.equals(songId, that.songId) && Objects.equals(personalPlays, that.personalPlays) && Objects.equals(personalValorations, that.personalValorations);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, songId, personalPlays, personalValorations);
    }

    @Override
    public String toString() {
        return "UserMusicDto{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", songId=" + songId +
                ", personalPlays=" + personalPlays +
                ", personalValorations=" + personalValorations +
                '}';
    }
}
