package com.bfu.courseProject.repositories;

import com.bfu.courseProject.entities.Album;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlbumRepository extends JpaRepository<Album, Long> {
}
