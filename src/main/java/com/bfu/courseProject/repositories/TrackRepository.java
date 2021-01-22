package com.bfu.courseProject.repositories;

import com.bfu.courseProject.entities.Track;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrackRepository extends JpaRepository<Track, Long> {

}
