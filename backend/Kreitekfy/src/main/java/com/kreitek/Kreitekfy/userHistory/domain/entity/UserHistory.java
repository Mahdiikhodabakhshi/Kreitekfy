package com.kreitek.Kreitekfy.userHistory.domain.entity;

import com.kreitek.Kreitekfy.user.domain.entity.User;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "userHistory")
public class UserHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userHistorySequence")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_username" , nullable = false)
    private User user;

    @Column(name = "song_title" ,nullable = false)
    private String songTitle;

    @Column(name = "history_date" , nullable = false)
    private Date historyDate;

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

    public String getSongTitle() {
        return songTitle;
    }

    public void setSongTitle(String songTitle) {
        this.songTitle = songTitle;
    }

    public Date getHistoryDate() {
        return historyDate;
    }

    public void setHistoryDate(Date historyDate) {
        this.historyDate = historyDate;
    }
}
