package com.kreitek.Kreitekfy.user.application.service;



import com.kreitek.Kreitekfy.user.application.dto.UserDetailsResponseDto;
import com.kreitek.Kreitekfy.user.application.dto.UserDto;

import java.util.Optional;

public interface AuthService {
    public UserDto register(UserDto userDto);
    Optional<UserDto> getUser(String username);
    Optional<UserDetailsResponseDto> getUserDetails(String username);
    UserDto updateUser(UserDto userDto);

}
