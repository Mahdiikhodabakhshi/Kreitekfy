package com.kreitek.Kreitekfy.album.application.dto;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

public class AlbumSimpleDto implements Serializable {
    private Long id;

    private String title;

    private byte[] image;

    private String imageType;

    public AlbumSimpleDto() {
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AlbumSimpleDto that = (AlbumSimpleDto) o;
        return Objects.equals(id, that.id) && Objects.equals(title, that.title) && Objects.deepEquals(image, that.image) && Objects.equals(imageType, that.imageType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, Arrays.hashCode(image), imageType);
    }

    @Override
    public String toString() {
        return "AlbumSimpleDto{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", image=" + Arrays.toString(image) +
                ", imageType='" + imageType + '\'' +
                '}';
    }
}
