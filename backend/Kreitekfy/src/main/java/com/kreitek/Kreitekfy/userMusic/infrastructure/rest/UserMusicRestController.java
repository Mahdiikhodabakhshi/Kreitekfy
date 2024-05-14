package com.kreitek.Kreitekfy.userMusic.infrastructure.rest;

import com.kreitek.Kreitekfy.category.domain.entity.Category;
import com.kreitek.Kreitekfy.userMusic.application.dto.UserMusicDto;
import com.kreitek.Kreitekfy.userMusic.application.service.UserMusicService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@RestController
@RequestMapping("/api")
public class UserMusicRestController {
    private final UserMusicService userMusicService;

    public UserMusicRestController(UserMusicService userMusicService) {
        this.userMusicService = userMusicService;
    }

    @GetMapping(value = "/usm" , produces = "application/json")
    public ResponseEntity<List<Category>> getUserMusic(@RequestParam("userId") String userId) {
        List<Category> userMusicDto = userMusicService.findUserMusicByUserEmail(userId);
//        List<Object> list = new ArrayList<>();
//        list.add(userMusicDto.get(0).clone());
//        list.add(Arrays.stream(userMusicDto.get(1)).toArray()[0]);
//        list.add(Arrays.stream(userMusicDto.get(0)).toArray()[0]);
        return ResponseEntity.ok(userMusicDto);
    }



    @GetMapping(value = "/user-music" , produces = "application/json")
    public ResponseEntity<UserMusicDto> getUserMusic(@RequestParam("songId") Long songId , @RequestParam("userId") String userId) {
        UserMusicDto userMusicDto = userMusicService.findUserMusicBySongIdAndUserName(songId, userId);
        return ResponseEntity.ok(userMusicDto);
    }

    @PostMapping(value = "/user-music" , produces = "application/json" , consumes = "application/json")
    public ResponseEntity<UserMusicDto> addUserMusic(@RequestBody UserMusicDto userMusicDto) {
        userMusicDto = userMusicService.saveUserMusic(userMusicDto);
        return ResponseEntity.ok(userMusicDto);
    }

    @PutMapping(value = "/user-music/view" , produces = "application/json" , consumes = "application/json")
    public ResponseEntity<UserMusicDto> updateUserMusicPlaying(@RequestBody UserMusicDto userMusicDto) {
        userMusicDto = userMusicService.updateUserMusicPlaying(userMusicDto.getSongId(), userMusicDto.getUserId());
        return ResponseEntity.ok(userMusicDto);
    }
    @PutMapping(value = "/user-music/rating" , produces = "application/json" , consumes = "application/json")
    public ResponseEntity<UserMusicDto> updateUserMusicRaiting(@RequestBody UserMusicDto userMusicDto) {
        userMusicDto = userMusicService.updateUserMusicRating(userMusicDto.getSongId(),userMusicDto.getUserId(),userMusicDto.getPersonalValorations());
        return ResponseEntity.ok(userMusicDto);
    }
}
