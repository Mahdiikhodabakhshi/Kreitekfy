package com.kreitek.Kreitekfy.userHistory.application.mapper;

import com.kreitek.Kreitekfy.genMapper.EntityMapper;

import com.kreitek.Kreitekfy.user.application.mapper.UserMapper;
import com.kreitek.Kreitekfy.userHistory.application.dto.UserHistoryDto;
import com.kreitek.Kreitekfy.userHistory.domain.entity.UserHistory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring" , uses = {UserMapper.class })
public interface UserHistoryMapper extends EntityMapper<UserHistoryDto, UserHistory> {

    @Override
    @Mapping(source = "userId" , target = "user")
    UserHistory toEntity(UserHistoryDto dto);

    @Override
    @Mapping(source = "user.username" , target = "userId")
    UserHistoryDto toDto(UserHistory entity);

    default UserHistory fromId(Long id){
        if (id == null) return null;
        UserHistory userHistory = new UserHistory();
        userHistory.setId(id);
        return userHistory;
    }
}
