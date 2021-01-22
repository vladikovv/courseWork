package com.bfu.courseProject.repositories;

import com.bfu.courseProject.entities.Artist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistRepository extends JpaRepository<Artist, Long> {

}
