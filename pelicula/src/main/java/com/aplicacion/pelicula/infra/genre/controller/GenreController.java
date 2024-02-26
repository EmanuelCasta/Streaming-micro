package com.aplicacion.pelicula.infra.genre.controller;


import com.aplicacion.pelicula.application.dto.command.GenreCreateDTO;
import com.aplicacion.pelicula.application.dto.queries.GenreAllDTO;
import com.aplicacion.pelicula.application.handleCommand.GenreHanlder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/genre/")
public class GenreController {
    private  final GenreHanlder genreHanlder;


    public GenreController(GenreHanlder genreHanlder) {
        this.genreHanlder = genreHanlder;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void createMovie(@RequestBody GenreCreateDTO genreCreateDTO){
        this.genreHanlder.handle(genreCreateDTO);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public GenreAllDTO getAllGenre(){
        return this.genreHanlder.handle();
    }
}
