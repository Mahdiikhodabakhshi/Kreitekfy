package com.kreitek.Kreitekfy.album.application.mapper;

import com.kreitek.Kreitekfy.album.application.dto.AlbumDto;
import com.kreitek.Kreitekfy.album.application.dto.AlbumSimpleDto;
import com.kreitek.Kreitekfy.album.domain.entity.Album;
import com.kreitek.Kreitekfy.artist.application.mapper.ArtistMapper;
import com.kreitek.Kreitekfy.genMapper.EntityMapper;
import com.kreitek.Kreitekfy.song.application.mapper.SongMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring" , uses = {ArtistMapper.class,SongMapper.class})
public interface AlbumMapper extends EntityMapper<AlbumDto, Album> {

    @Override
    @Mapping(target = "songs", ignore = true)
    Album toEntity(AlbumDto dto);

    List<AlbumSimpleDto> toSimpleDto(List<Album> albums);

    @Mapping(target = "songs", ignore = true)
    Album toDto(AlbumSimpleDto albumSimpleDto);

    default Album fromId(Long id){
        if (id == null) return null;
        Album album = new Album();
        album.setId(id);
        return album;}
}
