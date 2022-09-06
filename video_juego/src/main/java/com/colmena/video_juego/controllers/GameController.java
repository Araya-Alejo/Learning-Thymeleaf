package com.colmena.video_juego.controllers;

import com.colmena.video_juego.entities.Game;
import com.colmena.video_juego.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class GameController {

    @Autowired
    private GameService gameService;

    @GetMapping(value = "/start")
    public String start( Model model ) {
        try {
            List< Game > games = this.gameService.findAllByActive();
            model.addAttribute( "games", games );
            return "views/start";
        } catch ( Exception e ){
            return "";
        }
    }

}
