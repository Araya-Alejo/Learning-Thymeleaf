package com.colmena.video_juego.services;

import com.colmena.video_juego.entities.Studio;
import com.colmena.video_juego.repositories.StudioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class StudioService implements BaseService< Studio > {

    @Autowired
    private StudioRepository studioRepository;

    @Override
    @Transactional
    public List< Studio > findAll( ) throws Exception {
        try {
            return this.studioRepository.findAll( );
        } catch ( Exception e ) {
            throw new Exception( e.getMessage( ) );
        }
    }

    @Override
    @Transactional
    public Studio findById( long id ) throws Exception {
        try {
            Optional< Studio > entities = this.studioRepository.findById( id );
            return entities.get( );
        } catch ( Exception e ) {
            throw new Exception( e.getMessage( ) );
        }
    }

    @Override
    @Transactional
    public Studio save( Studio entity ) throws Exception {
        try {
            return this.studioRepository.save( entity );
        } catch ( Exception e ) {
            throw new Exception( e.getMessage( ) );
        }
    }

    @Override
    @Transactional
    public Studio update( Studio entity, long id ) throws Exception {
        try {
            return this.studioRepository.save( entity );
        } catch ( Exception e ) {
            throw new Exception( e.getMessage( ) );
        }
    }

    @Override
    @Transactional
    public boolean deleteById( long id ) throws Exception {
        try {
            Optional< Studio > optionalGame = this.studioRepository.findById( id );
            if ( !optionalGame.isEmpty( ) ) {
                Studio studio = optionalGame.get( );
                studio.setActive( !studio.isActive( ) );
                this.studioRepository.save( studio );
            } else {
                throw new Exception( );
            }
            return true;
        } catch ( Exception e ) {
            throw new Exception( e.getMessage( ) );
        }
    }
}
