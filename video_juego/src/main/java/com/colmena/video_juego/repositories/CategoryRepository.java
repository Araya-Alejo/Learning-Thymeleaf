package com.colmena.video_juego.repositories;

import com.colmena.video_juego.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository< Category, Long > {
}
