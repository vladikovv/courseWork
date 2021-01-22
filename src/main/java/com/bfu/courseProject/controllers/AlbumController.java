package com.bfu.courseProject.controllers;

import com.bfu.courseProject.dtos.album.AlbumRequestDTO;
import com.bfu.courseProject.dtos.album.AlbumResponseDTO;
import com.bfu.courseProject.entities.Track;
import com.bfu.courseProject.services.AlbumService;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
public class AlbumController {

    private final AlbumService albumService;

    @Autowired
    public AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }

    @GetMapping("/albums")
    public List<AlbumResponseDTO> getAllAlbums() {
        return albumService.getAll();
    }

    @GetMapping("/albums/{albumName}")
    public AlbumResponseDTO getAlbumByName(@PathVariable("albumName") @NotNull String name) {
        return albumService.getAlbumByName(name);
    }

    @GetMapping("/albums/{albumName}/tracks")
    public Set<Track> getAllTracksInAlbum(@PathVariable("albumName") @NotNull String name) {
        return albumService.getAllTracksInAlbum(name);
    }

    @DeleteMapping("/albums/{albumName}")
    public void deleteAlbumByName(@PathVariable("albumName") @NotNull String name) {
        albumService.deleteAlbum(name);
    }

    @PostMapping("/albums")
    @ResponseStatus(HttpStatus.CREATED)
    public AlbumResponseDTO createAlbum(@RequestBody AlbumRequestDTO albumRequestDTO) {
        return albumService.createAlbum(albumRequestDTO);
    }
}
