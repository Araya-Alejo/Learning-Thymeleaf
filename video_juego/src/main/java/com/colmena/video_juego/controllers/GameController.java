package com.colmena.video_juego.controllers;

import com.colmena.video_juego.entities.Game;
import com.colmena.video_juego.services.CategoryService;
import com.colmena.video_juego.services.GameService;
import com.colmena.video_juego.services.StudioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.validation.Valid;
import java.nio.file.Files;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

@Controller
public class GameController {

    @Autowired
    private GameService gameService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private StudioService studioService;

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
    public String crudGame( Model model ) {
        try {
            List< Game > games = this.gameService.findAll( );
            model.addAttribute( "games", games );
            return "views/crud";
        } catch ( Exception e ) {
            model.addAttribute( "error", e.getMessage( ) );
            return "error";
        }
    }

    @GetMapping ( "/form/video_game/{id}" )
    public String formVideoGame( Model model, @PathVariable ( "id" ) long id ) {
        try {
            model.addAttribute( "categories", this.categoryService.findAll( ) );
            model.addAttribute( "studios", this.studioService.findAll( ) );
            if ( id == 0 ) {
                model.addAttribute( "game", new Game( ) );
            } else {
                model.addAttribute( "game", this.gameService.findById( id ) );
            }
            return "views/form/video_game";
        } catch ( Exception e ) {
            model.addAttribute( "error", e.getMessage( ) );
            return "error";
        }
    }

    @PostMapping ( "/form/video_game/{id}" )
    public String saveVideoGame( @Valid @ModelAttribute ( "game" ) Game game, BindingResult result, @RequestParam ( "file" ) MultipartFile file,Model model, @PathVariable ( "id" ) long id ) {

        try {
            model.addAttribute( "categories", this.categoryService.findAll( ) );
            model.addAttribute( "studios", this.studioService.findAll( ) );
            if ( result.hasErrors( ) ) {
                return "views/form/video_game";
            }

            String path = "C://photo_of_games";
            int index = file.getOriginalFilename( ) .indexOf( "." );
            String extension;
            extension = "." + file.getOriginalFilename( ).substring( index + 1 );
            String photoName = Calendar.getInstance( ).getTimeInMillis( ) + extension;
            Path absolutePath = (id != 0 ? Paths.get(path + "//"+game.getPath_img()) :
                                Paths.get(path+"//"+photoName));
            if(id==0){
//                if(file.isEmpty()){
//                    model.addAttribute("errorImageMsg","The imagen can't be empty");
//                    return "views/form/video_game";
//                }
//                if(!this.extensionValidation(file)){
//                    model.addAttribute("errorImageMsg","Exception isn't validate");
//                    return "views/form/video_game";
//                }
//                if(file.getSize() >= 15000000){
//                    model.addAttribute("errorImageMsg","Weight exceeds 15MB");
//                    return "views/form/video_game";
//                }
                Files.write(absolutePath,file.getBytes());
                game.setPath_img(photoName);
                this.gameService.save(game);
            }else{
                if(!file.isEmpty()){
//                    if(!this.extensionValidation(file)){
//                        model.addAttribute("errorImageMsg","Exception isn't validat");
//                        return "views/form/video_game";
//                    }
//                    if(file.getSize() >= 15000000){
//                        model.addAttribute("errorImageMsg","Weight exceeds 15MB");
//                        return "views/form/video_game";
//                    }
                    Files.write(absolutePath,file.getBytes());
                }
                this.gameService.update(game,id);
            }
            return "redirect:/crud";
        }catch(Exception e){
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }

        public boolean extensionValidation(MultipartFile file){
            try {
                ImageIO.read(file.getInputStream()).toString();
                return true;
            }catch (Exception e){
                System.out.println(e);
                return false;
            }
        }



        @GetMapping ( "/delete/video_game/{id}" )
        public String deleteVideoGame ( Model model,@PathVariable ( "id" ) long id ){
            try {
                model.addAttribute( "game", this.gameService.findById( id ) );
                return "views/form/delete";
            } catch ( Exception e ) {
                model.addAttribute( "error", e.getMessage( ) );
                return "error";
            }
        }

        @PostMapping ( "/delete/video_game/{id}" )
        public String disabledVideoGame ( Model model,@PathVariable ( "id" ) long id ){
            try {
                this.gameService.deleteById( id );
                return "redirect:/crud";
            } catch ( Exception e ) {
                model.addAttribute( "error", e.getMessage( ) );
                return "error";
            }
        }

    }
