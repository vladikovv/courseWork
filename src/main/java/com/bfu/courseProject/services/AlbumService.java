package com.bfu.courseProject.services;

import com.bfu.courseProject.dtos.album.AlbumRequestDTO;
import com.bfu.courseProject.dtos.album.AlbumResponseDTO;
import com.bfu.courseProject.entities.Album;
import com.bfu.courseProject.entities.Track;

import java.util.List;
import java.util.Set;

public interface AlbumService {
    List<AlbumResponseDTO> getAll();

    AlbumResponseDTO getAlbumByName(String name);

    AlbumResponseDTO createAlbum(AlbumRequestDTO albumRequestDTO);

    void deleteAlbum(String name);

    AlbumResponseDTO editAlbumByName(String name, AlbumRequestDTO albumRequestDTO);

    Set<Track> getAllTracksInAlbum(String albumName);
}
