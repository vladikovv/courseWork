package com.bfu.courseProject.services.implementations;

import com.bfu.courseProject.dtos.track.TrackRequestDTO;
import com.bfu.courseProject.dtos.track.TrackResponseDTO;
import com.bfu.courseProject.entities.Album;
import com.bfu.courseProject.entities.Track;
import com.bfu.courseProject.exceptions.UserNotFoundException;
import com.bfu.courseProject.repositories.AlbumRepository;
import com.bfu.courseProject.repositories.TrackRepository;
import com.bfu.courseProject.services.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class TrackServiceImpl implements TrackService {

    private final TrackRepository trackRepository;
    private final AlbumRepository albumRepository;

    @Autowired
    public TrackServiceImpl(TrackRepository trackRepository, AlbumRepository albumRepository) {
        this.trackRepository = trackRepository;
        this.albumRepository = albumRepository;
    }

    @Override
    public TrackResponseDTO findTrackByName(String name) {
        return trackRepository.findAll()
                .stream()
                .filter(t -> t.getName().equals(name))
                .map(TrackResponseDTO::trackToTrackResponseDto)
                .findAny()
                .orElseThrow(() -> new UserNotFoundException("Track not found"));
    }

    @Override
    @Transactional
    public TrackResponseDTO createTrack(TrackRequestDTO trackRequestDTO) {
        Track track = trackRequestDtoToTrack(trackRequestDTO);
        trackRepository.save(track);
        return TrackResponseDTO.trackToTrackResponseDto(track);
    }

    @Override
    @Transactional
    public void deleteTrackByName(String name) {
        Track track = trackRepository.findAll()
                .stream()
                .filter(t -> t.getName().equals(name))
                .findAny()
                .orElseThrow(() -> new UserNotFoundException("Track not found"));
        trackRepository.deleteById(track.getId());

    }

    private Track trackRequestDtoToTrack(TrackRequestDTO trackRequestDTO) {
        Track track = new Track();
        track.setName(trackRequestDTO.getName());
        track.setLength(trackRequestDTO.getLength());

        Album album = albumRepository.findById(trackRequestDTO.getAlbumId())
                .orElseThrow(() -> new UserNotFoundException("Album does not exist"));
        track.setAlbum(album);

        return track;
    }


}
