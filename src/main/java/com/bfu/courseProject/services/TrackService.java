package com.bfu.courseProject.services;

import com.bfu.courseProject.dtos.track.TrackRequestDTO;
import com.bfu.courseProject.dtos.track.TrackResponseDTO;

public interface TrackService {
    TrackResponseDTO findTrackByName(String name);
    TrackResponseDTO createTrack(TrackRequestDTO trackRequestDTO);
    void deleteTrackByName(String name);
}
