package com.bfu.courseProject.controllers;

import com.bfu.courseProject.dtos.track.TrackRequestDTO;
import com.bfu.courseProject.dtos.track.TrackResponseDTO;
import com.bfu.courseProject.services.TrackService;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class TrackController {
    private final TrackService trackService;

    @Autowired
    public TrackController(TrackService trackService) {
        this.trackService = trackService;
    }

    @GetMapping("/tracks/{trackName}")
    public TrackResponseDTO findTrackByName(@PathVariable("trackName") @NotNull String name) {
        return trackService.findTrackByName(name);
    }

    @PostMapping("/tracks")
    @ResponseStatus(HttpStatus.CREATED)
    public TrackResponseDTO createTrack(@RequestBody TrackRequestDTO trackRequestDTO) {
        return trackService.createTrack(trackRequestDTO);
    }

    @DeleteMapping("/tracks/{trackName}")
    public void deleteTrack(@PathVariable("trackName") String name) {
        trackService.deleteTrackByName(name);
    }
}
