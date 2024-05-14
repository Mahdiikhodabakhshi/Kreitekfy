package com.kreitek.Kreitekfy.album.application.dto;





import com.kreitek.Kreitekfy.song.application.dto.SongSimpleDTO;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;


public class AlbumDto implements Serializable {
    private Long id;

    private String title;

    private byte[] image;

    private String imageType;

    private List<SongSimpleDTO> songs;

    public AlbumDto() {
    }

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

    public List<SongSimpleDTO> getSongs() {
        return songs;
    }

    public void setSongs(List<SongSimpleDTO> songs) {
        this.songs = songs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AlbumDto albumDto = (AlbumDto) o;
        return Objects.equals(id, albumDto.id) && Objects.equals(title, albumDto.title) && Objects.deepEquals(image, albumDto.image) && Objects.equals(imageType, albumDto.imageType) && Objects.equals(songs, albumDto.songs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, Arrays.hashCode(image), imageType, songs);
    }

    @Override
    public String toString() {
        return "AlbumDto{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", image=" + Arrays.toString(image) +
                ", imageType='" + imageType + '\'' +
                ", songs=" + songs +
                '}';
    }
}
