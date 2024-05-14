package com.kreitek.Kreitekfy.artist.infraestructure.rest;


import com.kreitek.Kreitekfy.artist.application.dto.ArtistDTO;
import com.kreitek.Kreitekfy.artist.application.service.ArtistService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ArtistRestController {

    private final ArtistService artistService;

    public ArtistRestController(ArtistService artistService) {
        this.artistService = artistService;
    }

    @GetMapping(value = "/artists/pageable",produces = "application/json")
    public ResponseEntity<Page<ArtistDTO>> getArtistByCriteriaPaged(@RequestParam(value = "filter", required = false) String filter, Pageable pageable){

        Page<ArtistDTO> artist = this.artistService.getArtistByCriteriaPaged(pageable, filter);
        return new ResponseEntity<>(artist, HttpStatus.OK);
    }

    @GetMapping(value = "/artists", produces = "application/json")
    public ResponseEntity<List<ArtistDTO>> getArtist() {
        List<ArtistDTO> artist = this.artistService.getArtists();

        return new ResponseEntity<>(artist, HttpStatus.OK);
    }

    @GetMapping(value = "/artists/{artistId}", produces = "application/json")
    public ResponseEntity<ArtistDTO> getArtistById(@PathVariable Long artistId){
        Optional<ArtistDTO> artist = this.artistService.getArtistById(artistId);
        return artist
                .map(artistDTO -> new ResponseEntity<>(artistDTO, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping(value = "/artists",produces = "application/json" , consumes = "application/json")
    public ResponseEntity<ArtistDTO> insertArtist(@RequestBody ArtistDTO artistDTO){
        artistDTO = this.artistService.saveArtist(artistDTO);
        return new ResponseEntity<>(artistDTO, HttpStatus.CREATED);
    }

    @PatchMapping(value = "/artists",produces = "application/json" , consumes = "application/json")
    public ResponseEntity<ArtistDTO> updateArtist(@RequestBody ArtistDTO artistDTO){
        artistDTO = this.artistService.saveArtist(artistDTO);
        return new ResponseEntity<>(artistDTO, HttpStatus.OK);
    }

    @DeleteMapping(value = "/artists/{artistId}",produces = "application/json" )
    public ResponseEntity<ArtistDTO> deleteArtist(@PathVariable Long artistId){
        this.artistService.deleteArtist(artistId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}