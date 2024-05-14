package com.kreitek.Kreitekfy.album.domain.entity;

import com.kreitek.Kreitekfy.song.domain.entity.Song;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "albums")
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "AlbumSequence")
    @Column(name = "id" , nullable = false)
    private Long id;

    @Column(name = "title" , nullable = false)
    private String title;

    @Lob
    private byte[] image;

    @Column(name = "image_type")
    private String imageType;

    @OneToMany(mappedBy = "album" , cascade = CascadeType.ALL)
    private Set<Song> songs;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getImageType() {
        return imageType;
    }

    public void setImageType(String imageType) {
        this.imageType = imageType;
    }

    public Set<Song> getSongs() {
        return songs;
    }

    public void setSongs(Set<Song> songs) {
        this.songs = songs;
    }
}
