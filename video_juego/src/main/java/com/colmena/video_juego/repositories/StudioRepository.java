package com.colmena.video_juego.repositories;

import com.colmena.video_juego.entities.Game;
import com.colmena.video_juego.entities.Studio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudioRepository extends JpaRepository< Studio, Long > {
    @Query ( value = "SELECT * FROM studio WHERE studio.active = true", nativeQuery = true )
    List< Studio > findAllByActive( );

    @Query ( value = "SELECT * FROM studio WHERE studio.id = :id AND studio.active = true", nativeQuery = true )
    Optional< Studio > findByIdAndActive( @Param ( "id" ) long id );
}
