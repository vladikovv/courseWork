package com.bfu.courseProject.services.implementations;

import com.bfu.courseProject.dtos.artist.ArtistRequestDTO;
import com.bfu.courseProject.dtos.artist.ArtistResponseDTO;
import com.bfu.courseProject.entities.Artist;
import com.bfu.courseProject.exceptions.UserNotFoundException;
import com.bfu.courseProject.repositories.ArtistRepository;
import com.bfu.courseProject.services.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArtistServiceImpl implements ArtistService {

    private final ArtistRepository artistRepository;

    @Autowired
    public ArtistServiceImpl(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    @Override
    public List<ArtistResponseDTO> getAll() {
        return artistRepository.findAll()
                .stream()
                .map(ArtistResponseDTO::responseFromArtist)
                .collect(Collectors.toList());
    }

    @Override
    public ArtistResponseDTO getById(Long id) {
        Artist artist =  artistRepository
                .findById(id)
                .orElseThrow(() -> new UserNotFoundException("Customer not found"));
        return ArtistResponseDTO.responseFromArtist(artist);
    }

    @Override
    @Transactional
    public ArtistResponseDTO create(ArtistRequestDTO artistRequestDTO) {
        Artist artist = artistFromArtistRequestDto(artistRequestDTO);
        artist.setAlbums(new HashSet<>());
        artistRepository.save(artist);
        return ArtistResponseDTO.responseFromArtist(artist);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Artist artist = artistRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Artist not found"));
        artistRepository.deleteById(id);
    }

    @Override
    @Transactional
    public ArtistResponseDTO edit(ArtistRequestDTO artistRequestDTO, Long id) {
        Artist artistToEdit = artistRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Artist not found"));

        artistToEdit.setName(artistRequestDTO.getName());
        artistToEdit.setDescription(artistRequestDTO.getDescription());
        artistRepository.save(artistToEdit);
        return ArtistResponseDTO.responseFromArtist(artistToEdit);
    }

    private Artist artistFromArtistRequestDto(ArtistRequestDTO artistRequestDTO) {
        Artist artist = new Artist();
        artist.setName(artistRequestDTO.getName());
        artist.setDescription(artistRequestDTO.getDescription());
        return artist;
    }
}
