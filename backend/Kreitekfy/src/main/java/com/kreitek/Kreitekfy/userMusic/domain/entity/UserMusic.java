package com.kreitek.Kreitekfy.userMusic.domain.entity;

import com.kreitek.Kreitekfy.song.domain.entity.Song;
import com.kreitek.Kreitekfy.user.domain.entity.User;
import jakarta.persistence.*;

@Entity
@Table(name = "userMusic")
public class UserMusic {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userMusicSequence")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_username" , nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "song_id" , nullable = false)
    private Song song;


    @Column(name = "personalPlays")
    private Long personalPlays;

    @Column(name = "personalValorations")
    private Long personalValorations;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Song getSong() {
        return song;
    }

    public void setSong(Song song) {
        this.song = song;
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
}
