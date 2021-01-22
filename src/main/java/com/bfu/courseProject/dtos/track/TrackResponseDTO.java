package com.bfu.courseProject.dtos.track;

import com.bfu.courseProject.entities.Track;

public class TrackResponseDTO {
    private Long id;
    private String name;
    private String length;
    private String albumName;

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

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public static TrackResponseDTO trackToTrackResponseDto(Track track) {
        TrackResponseDTO trackResponseDTO = new TrackResponseDTO();
        trackResponseDTO.setId(track.getId());
        trackResponseDTO.setAlbumName(track.getAlbum().getTitle());
        trackResponseDTO.setName(track.getName());
        trackResponseDTO.setLength(track.getLength());
        return trackResponseDTO;
    }
}
