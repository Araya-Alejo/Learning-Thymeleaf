package com.colmena.video_juego.repositories;

import com.colmena.video_juego.entities.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GameRepository extends JpaRepository< Game, Long > {
    @Query ( value = "SELECT * FROM game WHERE game.active = true", nativeQuery = true )
    List< Game > findAllByActive( );

    @Query ( value = "SELECT * FROM game WHERE game.id = :id AND game.active = true", nativeQuery = true )
    Optional< Game > findByIdAndActive( @Param ( "id" ) long id );
}
