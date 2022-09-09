package com.colmena.video_juego.repositories;

import com.colmena.video_juego.entities.Category;
import com.colmena.video_juego.entities.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository< Category, Long > {
    @Query ( value = "SELECT * FROM category WHERE category.active = true", nativeQuery = true )
    List< Category > findAllByActive( );

    @Query ( value = "SELECT * FROM category WHERE category.id = :id AND category.active = true", nativeQuery = true )
    Optional< Category > findByIdAndActive( @Param ( "id" ) long id );

}
