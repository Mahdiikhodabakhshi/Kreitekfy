package com.kreitek.Kreitekfy.user.infraestructure.rest;

import com.kreitek.Kreitekfy.user.application.dto.UserDetailsResponseDto;
import com.kreitek.Kreitekfy.user.application.dto.UserDto;
import com.kreitek.Kreitekfy.user.application.service.AuthService;
import com.kreitek.Kreitekfy.user.domain.entity.Role;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class userRestController {
    private final AuthService authService;
    private final PasswordEncoder passwordEncoder;

    public userRestController(AuthService authService, PasswordEncoder passwordEncoder) {
        this.authService = authService;
        this.passwordEncoder = passwordEncoder;
    }

//    @GetMapping(value = "/userDetails" , produces = "application/json")
//    public ResponseEntity<UserDetailsDto> getUserDetails(){
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String userId = authentication.getName();
//
//        return authService.getUserDetails(userId)
//                .map(userDto -> new ResponseEntity<>(userDto,HttpStatus.OK))
//                .orElseGet(()-> new ResponseEntity<>(HttpStatus.NOT_FOUND));
//    }

    @GetMapping(value = "/userDetails/{userId}" , produces = "application/json")
    public ResponseEntity<UserDetailsResponseDto> getUserDetails(@PathVariable("userId") String userIdFromHeader){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userIdFromToken = authentication.getName();

        if (userIdFromToken.equals( userIdFromHeader)){
            return authService.getUserDetails(userIdFromHeader)
                    .map(userDto -> new ResponseEntity<>(userDto,HttpStatus.OK))
                    .orElseGet(()-> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        }else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }


    }

    @PatchMapping(value = "/userDetails/{userId}" , produces = "application/json")
    public ResponseEntity<Void> updateUserDetails(@PathVariable("userId") String userIdFromHeader , @RequestBody UserDto userDto){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userIdFromToken = authentication.getName();

        if (userIdFromToken.equals( userIdFromHeader)){
            if (userDto.getPassword() != null){
                userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
            }
            authService.updateUser(userDto);
            return new ResponseEntity<>(HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }


    }



}
