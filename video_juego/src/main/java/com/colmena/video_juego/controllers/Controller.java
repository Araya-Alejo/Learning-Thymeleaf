package com.colmena.video_juego.controllers;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@org.springframework.stereotype.Controller
public class Controller {

    @GetMapping(value = "/")
    public String index( Model model){
        String message = "Bye, Arturo!";
        model.addAttribute( "message", message);
        return "index";
    }
}
