package com.kreitek.Kreitekfy.song.infrastructure.rest;


import com.kreitek.Kreitekfy.song.application.dto.SongDTO;
import com.kreitek.Kreitekfy.song.application.services.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
public class SongRestController  {

    private final SongService songService;

    @Autowired
    public SongRestController(SongService songService) {
        this.songService = songService;
    }

    @GetMapping(value = "/songs/pageable",produces = "application/json")
    ResponseEntity<Page<SongDTO>> getSongByCriteriaPaged(@RequestParam(value = "filter", required = false) String filter, Pageable pageable){

        System.out.println(filter);
        Page<SongDTO> songDTOS = songService.getSongByCriteriaPaged(pageable, filter);
        return new ResponseEntity<>(songDTOS, HttpStatus.OK);
    }

    @GetMapping(value = "/songs",produces = "application/json")
    ResponseEntity<List<SongDTO>> getSongs(@RequestParam(value = "category", required = false) String category){

        List<SongDTO> songs;
        if (category == null) {
             songs = songService.getSongs();
        }else {
            songs = songService.getTop5SongsByCategory(category);
        }



        return new ResponseEntity<>(songs, HttpStatus.OK);
    }

    @GetMapping(value = "/songs/{songId}", produces = "application/json")
    public ResponseEntity<SongDTO> getSongById(@PathVariable Long songId) {
        return songService
                .getSongById(songId)
                .map(songDTO -> new ResponseEntity<>(songDTO, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }

    @GetMapping(value = "/songs/newest", produces = "application/json")
    public ResponseEntity<List<SongDTO>> getNewestSongs(@RequestParam(value = "category", required = false) String category) {
        List<SongDTO> songs;
        if (category == null) {
            songs = songService.getAllSongsByOrderByUploadDateDesc();
        }else {
            songs = songService.getTop5SongsByCategory(category);
        }



        return new ResponseEntity<>(songs, HttpStatus.OK);
    }

    @GetMapping(value = "/songs/top-rated", produces = "application/json")
    public ResponseEntity<List<SongDTO>> getTopRatedSongs(@RequestParam(value = "categoryId", required = false) Long categoryId){
        List<SongDTO> songDTOS =songService.findByOrderByTotalRateDesc();
        return new ResponseEntity<>(songDTOS, HttpStatus.OK); //todo
    }

    @GetMapping(value = "/songs/top-view", produces = "application/json")
    public ResponseEntity<List<SongDTO>> getTopViewedSongs(@RequestParam(value = "categoryName", required = false) String categoryName){
        List<SongDTO> songDTOS ;
        if(categoryName!=null){
            songDTOS = songService.findByOrderCategoryByTotalViewsDesc(categoryName);
        }else{
            songDTOS =songService.findByOrderByTotalViewsDesc();
        }

        return new ResponseEntity<>(songDTOS, HttpStatus.OK);
    }


    @GetMapping(value = "/songs/recommend/{userId}", produces = "application/json")
    public ResponseEntity<List<SongDTO>> getForUSongs(@PathVariable String userId){
        List<SongDTO> songDTOS = songService.findByUserPreferences(userId);

        return new ResponseEntity<>(songDTOS, HttpStatus.OK);
    }

    @PostMapping(value = "/songs",produces = "application/json", consumes = "application/json")
    public ResponseEntity<SongDTO> saveSong(@RequestBody SongDTO songDTO) {
        songDTO = songService.saveSong(songDTO);
        return new ResponseEntity<>(songDTO,HttpStatus.CREATED);
    }

    @PatchMapping(value = "/songs",produces = "application/json", consumes = "application/json")
    public ResponseEntity<SongDTO> updateSong(@RequestBody SongDTO songDTO) {
        songDTO = songService.saveSong(songDTO);
        return new ResponseEntity<>(songDTO,HttpStatus.OK);
    }

    @DeleteMapping(value = "/songs/{idSong}")
    public  ResponseEntity<Void> deleteSong(@PathVariable Long idSong){
        songService.deleteSong(idSong);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
