package com.bfu.courseProject.controllers;

import com.bfu.courseProject.dtos.artist.ArtistRequestDTO;
import com.bfu.courseProject.dtos.artist.ArtistResponseDTO;
import com.bfu.courseProject.services.ArtistService;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ArtistController {

    private final ArtistService artistService;

    @Autowired
    public ArtistController(ArtistService artistService) {
        this.artistService = artistService;
    }

    @GetMapping("/artists")
    public List<ArtistResponseDTO> getAllArtists() {
        return artistService.getAll();
    }

    @GetMapping("/artists/{artistId}")
    public ArtistResponseDTO getArtistById(@PathVariable("artistId") @NotNull Long customerId) {
        return artistService.getById(customerId);
    }

    @PostMapping("/artists")
    @ResponseStatus(HttpStatus.CREATED)
    public ArtistResponseDTO createArtist(@RequestBody ArtistRequestDTO artistRequestDTO) {
        return artistService.create(artistRequestDTO);
    }

    @DeleteMapping("/artists/{artistId}")
    public void deleteArtist(@PathVariable("artistId") @NotNull Long id) {
        artistService.delete(id);
    }

    @PutMapping("/artists/{artistId}")
    public ArtistResponseDTO edit(@PathVariable("artistId") @NotNull Long id,
                                  @RequestBody ArtistRequestDTO artistRequestDTO) {
        return artistService.edit(artistRequestDTO, id);
    }
}
