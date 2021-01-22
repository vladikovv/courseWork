package com.bfu.courseProject.dtos.artist;

import com.bfu.courseProject.entities.Album;
import com.bfu.courseProject.entities.Artist;

import java.util.Set;

public class ArtistResponseDTO {
    private Long id;
    private String name;
    private String description;
    private Set<Album> albums;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(Set<Album> albums) {
        this.albums = albums;
    }

    public static ArtistResponseDTO responseFromArtist(Artist artist) {
        ArtistResponseDTO artistResponseDTO = new ArtistResponseDTO();
        artistResponseDTO.setId(artist.getId());
        artistResponseDTO.setName(artist.getName());
        artistResponseDTO.setDescription(artist.getDescription());
        artistResponseDTO.setAlbums(artist.getAlbums());
        return artistResponseDTO;
    }
}
