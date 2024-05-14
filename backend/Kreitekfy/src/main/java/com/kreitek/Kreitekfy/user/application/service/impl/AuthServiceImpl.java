package com.kreitek.Kreitekfy.user.application.service.impl;


import com.kreitek.Kreitekfy.user.application.dto.UserDetailsResponseDto;
import com.kreitek.Kreitekfy.user.application.dto.UserDto;
import com.kreitek.Kreitekfy.user.application.mapper.UserMapper;
import com.kreitek.Kreitekfy.user.application.service.AuthService;
import com.kreitek.Kreitekfy.user.domain.entity.Role;
import com.kreitek.Kreitekfy.user.domain.entity.User;
import com.kreitek.Kreitekfy.user.domain.persistence.UserPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {
    private final UserPersistence userPersistence;
    private final UserMapper userMapper;



    public AuthServiceImpl(UserPersistence userPersistence, UserMapper userMapper) {
        this.userPersistence = userPersistence;
        this.userMapper = userMapper;

    }

    public UserDto register(UserDto userDto) {
        User user = userMapper.toEntity(userDto);
        return userMapper.toDto(userPersistence.save(user));
    }

    @Override
    public Optional<UserDto> getUser(String username) {
        Optional<User> user = userPersistence.find(username);
        if (user.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(userMapper.toDto(user.get()));
    }

    @Override
    public Optional<UserDetailsResponseDto> getUserDetails(String username) {
        Optional<User> user = userPersistence.find(username);
        UserDetailsResponseDto userDetailsResponseDto = new UserDetailsResponseDto();
        return user.map(user1 -> {
            userDetailsResponseDto.setEmail(user1.getEmail());

            userDetailsResponseDto.setUsername(user1.getUsername());
            userDetailsResponseDto.setFirstName(user1.getFirstName());
            userDetailsResponseDto.setLastName(user1.getLastName());

            return userDetailsResponseDto;
        });
    }

    @Override
    public UserDto updateUser(UserDto userDto) {
        userDto.setRole(Role.USER);
        User user = userMapper.toEntity(userDto);
        User userUpdate = userPersistence.find(userDto.getUsername()).get();
        if (user.getPassword() == null){
            user.setPassword(userUpdate.getPassword());
        }



        user= userPersistence.save(user);



        return userMapper.toDto(user);
    }


}
