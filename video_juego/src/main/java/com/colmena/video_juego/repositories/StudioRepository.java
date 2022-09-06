package com.colmena.video_juego.repositories;

import com.colmena.video_juego.entities.Studio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudioRepository extends JpaRepository< Studio, Long > {
}
