package com.colmena.video_juego.services;

import com.colmena.video_juego.entities.Category;
import com.colmena.video_juego.entities.Studio;
import com.colmena.video_juego.repositories.CategoryRepository;
import com.colmena.video_juego.repositories.StudioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService implements BaseService< Category > {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    @Transactional
    public List< Category > findAll( ) throws Exception {
        try {
            return this.categoryRepository.findAll( );
        } catch ( Exception e ) {
            throw new Exception( e.getMessage( ) );
        }
    }

    @Override
    @Transactional
    public Category findById( long id ) throws Exception {
        try {
            Optional< Category > entities = this.categoryRepository.findById( id );
            return entities.get( );
        } catch ( Exception e ) {
            throw new Exception( e.getMessage( ) );
        }
    }

    @Override
    @Transactional
    public Category saveOne( Category entity ) throws Exception {
        try {
            return this.categoryRepository.save( entity );
        } catch ( Exception e ) {
            throw new Exception( e.getMessage( ) );
        }
    }

    @Override
    @Transactional
    public Category updateOne( Category entity, long id ) throws Exception {
        try {
            return this.categoryRepository.save( entity );
        } catch ( Exception e ) {
            throw new Exception( e.getMessage( ) );
        }
    }

    @Override
    @Transactional
    public boolean deleteById( long id ) throws Exception {
        try {
            Optional< Category > optionalStudio = this.categoryRepository.findById( id );
            if ( !optionalStudio.isEmpty( ) ) {
                Category category = optionalStudio.get( );
                category.setActive( !category.isActive( ) );
                this.categoryRepository.save( category );
            } else {
                throw new Exception( );
            }
            return true;
        } catch ( Exception e ) {
            throw new Exception( e.getMessage( ) );
        }
    }
}
