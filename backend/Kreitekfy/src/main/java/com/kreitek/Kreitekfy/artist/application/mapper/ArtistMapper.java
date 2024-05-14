package com.kreitek.Kreitekfy.artist.application.mapper;

import com.kreitek.Kreitekfy.artist.application.dto.ArtistDTO;
import com.kreitek.Kreitekfy.artist.domain.entity.Artist;
import com.kreitek.Kreitekfy.genMapper.EntityMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ArtistMapper extends EntityMapper<ArtistDTO, Artist> {
}
