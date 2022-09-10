package com.colmena.video_juego.services;

import com.colmena.video_juego.entities.Game;
import com.colmena.video_juego.repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class GameService implements BaseService< Game > {

    @Autowired
    private GameRepository gameRepository;

    @Override
    @Transactional
    public List< Game > findAll( ) throws Exception {
        try {
            return this.gameRepository.findAll( );
        } catch ( Exception e ) {
            throw new Exception( e.getMessage( ) );
        }
    }

    @Override
    @Transactional
    public Game findById( long id ) throws Exception {
        try {
            Optional< Game > entities = this.gameRepository.findById( id );
            return entities.get( );
        } catch ( Exception e ) {
            throw new Exception( e.getMessage( ) );
        }
    }

    @Override
    @Transactional
    public Game save( Game entity ) throws Exception {
        try {
            return this.gameRepository.save( entity );
        } catch ( Exception e ) {
            throw new Exception( e.getMessage( ) );
        }
    }

    @Override
    @Transactional
    public Game update( Game entity, long id ) throws Exception {
        try {
            Optional<Game> opt = this.gameRepository.findById(id);
            Game videojuego = opt.get();
            videojuego = this.gameRepository.save(entity);
            return videojuego;
        } catch ( Exception e ) {
            throw new Exception( e.getMessage( ) );
        }
    }

    @Override
    @Transactional
    public boolean deleteById( long id ) throws Exception {
        try {
            Optional< Game > optionalGame = this.gameRepository.findById( id );
            if ( !optionalGame.isEmpty( ) ) {
                Game game = optionalGame.get( );
                game.setActive( !game.isActive( ) );
                this.gameRepository.save( game );
            } else {
                throw new Exception( );
            }
            return true;
        } catch ( Exception e ) {
            throw new Exception( e.getMessage( ) );
        }
    }

    @Transactional
    public List< Game > findAllByActive( ) throws Exception {
        try {
            return this.gameRepository.findAllByActive( );
        } catch ( Exception e ) {
            throw new Exception( e.getMessage( ) );
        }
    }

    @Transactional
    public Game findAllByIdAndActive( long id ) throws Exception {
        try {
            Optional< Game > gameOptional = this.gameRepository.findByIdAndActive( id );
            return gameOptional.get( );
        } catch ( Exception e ) {
            throw new Exception( e.getMessage( ) );
        }
    }

    @Transactional
    public List< Game > findByTitle( String title ) throws Exception {
        try {
            return this.gameRepository.findByTitle( title );
        } catch ( Exception e ) {
            throw new Exception( e.getMessage( ) );
        }
    }
}
