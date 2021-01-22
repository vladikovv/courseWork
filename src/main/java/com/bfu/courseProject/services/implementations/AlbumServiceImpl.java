package com.bfu.courseProject.services.implementations;

import com.bfu.courseProject.dtos.album.AlbumRequestDTO;
import com.bfu.courseProject.dtos.album.AlbumResponseDTO;
import com.bfu.courseProject.entities.Album;
import com.bfu.courseProject.entities.Artist;
import com.bfu.courseProject.entities.Track;
import com.bfu.courseProject.entities.User;
import com.bfu.courseProject.exceptions.UserNotFoundException;
import com.bfu.courseProject.repositories.AlbumRepository;
import com.bfu.courseProject.repositories.ArtistRepository;
import com.bfu.courseProject.repositories.UserRepository;
import com.bfu.courseProject.services.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AlbumServiceImpl implements AlbumService {

    private final AlbumRepository albumRepository;
    private final UserRepository userRepository;
    private final ArtistRepository artistRepository;

    @Autowired
    public AlbumServiceImpl(AlbumRepository albumRepository,
                            UserRepository userRepository, ArtistRepository artistRepository) {
        this.albumRepository = albumRepository;
        this.userRepository = userRepository;
        this.artistRepository = artistRepository;
    }

    @Override
    public List<AlbumResponseDTO> getAll() {
        return albumRepository.findAll()
                .stream()
                .map(AlbumResponseDTO::responseFromAlbum)
                .collect(Collectors.toList());
    }

    @Override
    public AlbumResponseDTO getAlbumByName(String name) {
        Album album = albumRepository.findAll()
                .stream()
                .filter(t -> t.getTitle().equals(name))
                .findAny()
                .orElseThrow(() -> new UserNotFoundException("Album not found"));
        return AlbumResponseDTO.responseFromAlbum(album);
    }



    @Override
    public AlbumResponseDTO createAlbum(AlbumRequestDTO albumRequestDTO) {
        Album album = albumRequestDtoToTrack(albumRequestDTO);
        albumRepository.save(album);

        return AlbumResponseDTO.responseFromAlbum(album);
    }

    @Override
    public void deleteAlbum(String name) {
        Album album = albumRepository.findAll()
                .stream()
                .filter(t -> t.getTitle().equals(name))
                .findAny()
                .orElseThrow(() -> new UserNotFoundException("Album not found"));

        albumRepository.deleteById(album.getId());
    }



    @Override
    public AlbumResponseDTO editAlbumByName(String name, AlbumRequestDTO albumRequestDTO) {
        return null;
    }

    @Override
    public Set<Track> getAllTracksInAlbum(String albumName) {
        Album foundAlbum = albumRepository.findAll()
                .stream()
                .filter(t -> t.getTitle().equals(albumName))
                .findAny()
                .orElseThrow(() -> new UserNotFoundException("Album not found"));

        return new HashSet<>(foundAlbum.getTracks());
    }

    private Album albumRequestDtoToTrack(AlbumRequestDTO albumRequestDTO) {
        Album album = new Album();
        album.setTitle(albumRequestDTO.getTitle());
        album.setGenre(albumRequestDTO.getGenre());

        Artist artist = artistRepository.findById(albumRequestDTO.getArtistId())
                .orElseThrow(() -> new UserNotFoundException("Artist not found"));
        album.setArtist(artist);

        User user = userRepository.findById(albumRequestDTO.getUserId())
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        album.setUser(user);
        album.setPrice(albumRequestDTO.getPrice());
        album.setDateBought(albumRequestDTO.getDateBought().atStartOfDay(ZoneId.systemDefault()).toInstant());

        return album;
    }
}
