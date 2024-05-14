package com.kreitek.Kreitekfy.userHistory.application.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class UserHistoryDto implements Serializable {
    private Long id;
    private String userId;
    private String songTitle;
    private Date historyDate;

    public UserHistoryDto() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserHistoryDto that = (UserHistoryDto) o;
        return Objects.equals(id, that.id) && Objects.equals(userId, that.userId) && Objects.equals(songTitle, that.songTitle) && Objects.equals(historyDate, that.historyDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, songTitle, historyDate);
    }

    @Override
    public String toString() {
        return "UserHistoryDto{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", songTitle='" + songTitle + '\'' +
                ", historyDate=" + historyDate +
                '}';
    }
}
