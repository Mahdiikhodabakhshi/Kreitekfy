package com.kreitek.Kreitekfy.album.infrastructure.rest;

import com.kreitek.Kreitekfy.album.application.dto.AlbumDto;
import com.kreitek.Kreitekfy.album.application.dto.AlbumSimpleDto;
import com.kreitek.Kreitekfy.album.application.service.AlbumService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AlbumRestController {

    private final AlbumService albumService;


    public AlbumRestController(AlbumService albumService) {
        this.albumService = albumService;
    }

    @GetMapping(value = "/albums",produces = "application/json")
    public ResponseEntity<List<AlbumSimpleDto>> getAllAlbums() {
        List<AlbumSimpleDto> albumSimpleDtos = albumService.getAlbums();
        return ResponseEntity.ok(albumSimpleDtos);
    }

    @GetMapping(value = "/albums/search" , produces = "application/json")
    public ResponseEntity<Page<AlbumDto>> searchAlbum(@RequestParam(value = "filter" , required = false) String search , Pageable pageable) {
        Page<AlbumDto> albumDtos = albumService.getAlbumsByCriteriaPaged(pageable, search);
        return ResponseEntity.ok(albumDtos);
    }

    @GetMapping(value = "/albums/{albumId}", produces = "application/json")
    public ResponseEntity<AlbumDto> getAlbumById(@PathVariable Long albumId) {
        return albumService.getAlbumById(albumId)
                .map(albumDto -> new ResponseEntity<>(albumDto , HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping(value = "/albums" , produces = "application/json" , consumes = "application/json")
    public ResponseEntity<AlbumDto> createAlbum(@RequestBody AlbumDto albumDto) {
        AlbumDto album = albumService.saveAlbum(albumDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(album);
    }

    @PatchMapping(value = "/albums" , produces = "application/json" , consumes = "application/json")
    public ResponseEntity<AlbumDto> updateAlbum(@RequestBody AlbumDto albumDto) {
        AlbumDto updateAlbum = albumService.saveAlbum(albumDto);
        return ResponseEntity.ok(updateAlbum);
     }

    @DeleteMapping(value = "/albums/{albumId}", produces = "application/json")
    public ResponseEntity<AlbumDto> deleteAlbum(@PathVariable Long albumId) {
        albumService.deleteAlbum(albumId);
        return ResponseEntity.noContent().build();
    }


}
