package com.colmena.video_juego.controllers;

import com.colmena.video_juego.entities.Game;
import com.colmena.video_juego.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class GameController {

    @Autowired
    private GameService gameService;

    @GetMapping ( value = "/start" )
    public String start( Model model ) {
        try {
            List< Game > games = this.gameService.findAllByActive( );
            model.addAttribute( "games", games );
            return "views/start";
        } catch ( Exception e ) {
            model.addAttribute( "error", e.getMessage( ) );
            return "error";
        }
    }

    @GetMapping ( "/detail/{id}" )
    public String detailGame( Model model, @PathVariable ( "id" ) long id ) {
        try {
            Game game = this.gameService.findAllByIdAndActive( id );
            model.addAttribute( "game", game );
            return "views/detail";
        } catch ( Exception e ) {
            model.addAttribute( "error", e.getMessage( ) );
            return "error";
        }
    }

    @GetMapping ( value = "/search" )
    public String searchGame( Model model, @RequestParam ( value = "query", required = false ) String title ) {
        try {
            List< Game > games = this.gameService.findByTitle( title );
            model.addAttribute( "games", games );
            return "views/search";
        } catch ( Exception e ) {
            model.addAttribute( "error", e.getMessage( ) );
            return "error";
        }
    }

    @GetMapping ( value = "/crud" )
    public String crudGame( Model model) {
        try {
            List< Game > games = this.gameService.findAll();
            model.addAttribute( "games", games );
            return "views/crud";
        } catch ( Exception e ) {
            model.addAttribute( "error", e.getMessage( ) );
            return "error";
        }
    }
}
