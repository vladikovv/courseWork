package com.bfu.courseProject.dtos.album;

import com.bfu.courseProject.entities.Album;
import com.bfu.courseProject.entities.Track;

import java.time.Instant;
import java.util.Set;

public class AlbumResponseDTO {
    private Long id;
    private String name;
    private String genre;
    private Float price;
    private Instant dateBought;
    private Set<Track> songNames;

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

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Instant getDateBought() {
        return dateBought;
    }

    public void setDateBought(Instant dateBought) {
        this.dateBought = dateBought;
    }

    public Set<Track> getSongNames() {
        return songNames;
    }

    public void setSongNames(Set<Track> songNames) {
        this.songNames = songNames;
    }

    public static AlbumResponseDTO responseFromAlbum(Album album) {
        AlbumResponseDTO albumResponseDTO = new AlbumResponseDTO();
        albumResponseDTO.setId(album.getId());
        albumResponseDTO.setName(album.getTitle());
        albumResponseDTO.setGenre(album.getGenre());
        albumResponseDTO.setPrice(album.getPrice());
        albumResponseDTO.setDateBought(album.getDateBought());
        albumResponseDTO.setSongNames(album.getTracks());
        return albumResponseDTO;
    }
}
