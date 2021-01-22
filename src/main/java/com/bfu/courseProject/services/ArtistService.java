package com.bfu.courseProject.services;

import com.bfu.courseProject.dtos.artist.ArtistRequestDTO;
import com.bfu.courseProject.dtos.artist.ArtistResponseDTO;

import java.util.List;

public interface ArtistService {
    List<ArtistResponseDTO> getAll();

    ArtistResponseDTO getById(Long id);

    ArtistResponseDTO create(ArtistRequestDTO artistRequestDTO);

    void delete(Long id);

    ArtistResponseDTO edit(ArtistRequestDTO artistRequestDTO, Long id);
}
