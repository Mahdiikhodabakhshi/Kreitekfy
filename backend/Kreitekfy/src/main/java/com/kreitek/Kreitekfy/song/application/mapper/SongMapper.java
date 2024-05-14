package com.kreitek.Kreitekfy.song.application.mapper;


import com.kreitek.Kreitekfy.album.application.mapper.AlbumMapper;
import com.kreitek.Kreitekfy.artist.application.mapper.ArtistMapper;
import com.kreitek.Kreitekfy.category.application.mapper.CategoryMapper;
import com.kreitek.Kreitekfy.genMapper.EntityMapper;
import com.kreitek.Kreitekfy.song.application.dto.SongDTO;
import com.kreitek.Kreitekfy.song.application.dto.SongSimpleDTO;
import com.kreitek.Kreitekfy.song.domain.entity.Song;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = { AlbumMapper.class, CategoryMapper.class, ArtistMapper.class})
public interface SongMapper extends EntityMapper<SongDTO, Song> {

    @Override
    @Mapping(source = "albumId", target = "album")
    @Mapping(source = "categoryId", target = "category")
    Song toEntity(SongDTO songDTO);
    
    @Override
    @Mapping(source = "album.id", target = "albumId")
    @Mapping(source = "album.title", target = "albumTitle")
    @Mapping(source = "album.image", target = "albumImage")
    @Mapping(source = "album.imageType", target = "albumImageType")
    @Mapping(source = "category.id", target = "categoryId")
    @Mapping(source = "category.name", target = "categoryName")
    SongDTO toDto(Song song);

    @Mapping(source = "category.id", target = "categoryId")
    @Mapping(source = "category.name", target = "categoryName")
    SongSimpleDTO toSimpleDto(Song song);

    @Mapping(source = "categoryId", target = "category")
    @Mapping(target = "album", ignore = true)
    Song toEntity(SongSimpleDTO songSimpleDTO);

    @Mapping(source = "category.id", target = "categoryId")
    @Mapping(source = "category.name", target = "categoryName")
    List<SongSimpleDTO> toSimpleDto(List<Song> songs);

    default Song fromId(Long id) {
        if (id == null) return null;

        Song song = new Song();
        song.setId(id);
        return song;
    }

}
