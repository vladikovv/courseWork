package com.bfu.courseProject.dtos.album;

import java.time.Instant;
import java.time.LocalDate;

public class AlbumRequestDTO {
    private String title;
    private String genre;
    private Float price;
    private LocalDate dateBought;
    private Long userId;
    private Long artistId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getArtistId() {
        return artistId;
    }

    public void setArtistId(Long artistId) {
        this.artistId = artistId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public LocalDate getDateBought() {
        return dateBought;
    }

    public void setDateBought(LocalDate dateBought) {
        this.dateBought = dateBought;
    }
}
