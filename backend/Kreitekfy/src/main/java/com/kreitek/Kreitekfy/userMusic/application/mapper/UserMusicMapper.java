package com.kreitek.Kreitekfy.userMusic.application.mapper;

import com.kreitek.Kreitekfy.genMapper.EntityMapper;
import com.kreitek.Kreitekfy.song.application.mapper.SongMapper;
import com.kreitek.Kreitekfy.user.application.mapper.UserMapper;
import com.kreitek.Kreitekfy.userMusic.application.dto.UserMusicDto;
import com.kreitek.Kreitekfy.userMusic.domain.entity.UserMusic;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring" , uses = {UserMapper.class , SongMapper.class})
public interface UserMusicMapper extends EntityMapper<UserMusicDto,UserMusic> {

    @Override
    @Mapping(source = "userId" , target = "user")
    @Mapping(source = "songId" , target = "song")
    UserMusic toEntity(UserMusicDto dto);

    @Override
    @Mapping(source = "user.username" , target = "userId")
    @Mapping(source = "song.id" , target = "songId")
    UserMusicDto toDto(UserMusic entity);

    default UserMusic fromId(Long id) {
        if (id == null) return null;
        UserMusic userMusic = new UserMusic();
        userMusic.setId(id);
        return userMusic;
    }
}
