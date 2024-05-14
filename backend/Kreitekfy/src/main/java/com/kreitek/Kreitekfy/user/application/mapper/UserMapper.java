package com.kreitek.Kreitekfy.user.application.mapper;


import com.kreitek.Kreitekfy.genMapper.EntityMapper;
import com.kreitek.Kreitekfy.user.application.dto.UserDto;
import com.kreitek.Kreitekfy.user.domain.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper extends EntityMapper<UserDto, User> {



//    @Mapping(target = "password" , ignore = true)
//    UserDetailsDto toUserDetailsDto(User user);


    default User fromId(String username) {
        if (username == null) {
            return null;
        }
        User user = new User();
        user.setUsername(username);
        return user;
    }
}
