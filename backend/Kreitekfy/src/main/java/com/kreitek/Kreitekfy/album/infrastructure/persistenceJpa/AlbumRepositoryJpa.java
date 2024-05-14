package com.kreitek.Kreitekfy.album.infrastructure.persistenceJpa;

import com.kreitek.Kreitekfy.album.domain.entity.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AlbumRepositoryJpa extends JpaRepository<Album, Long> , JpaSpecificationExecutor<Album> {
}
