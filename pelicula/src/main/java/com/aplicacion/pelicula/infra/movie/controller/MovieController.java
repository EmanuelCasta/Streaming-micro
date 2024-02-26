package com.aplicacion.pelicula.infra.movie.controller;

import com.aplicacion.pelicula.application.dto.command.MovieCreateDTO;
import com.aplicacion.pelicula.application.dto.queries.MovieAllDTO;
import com.aplicacion.pelicula.application.dto.queries.MovieObjectIDTO;
import com.aplicacion.pelicula.application.handleCommand.MovieHanlder;
import com.aplicacion.pelicula.domain.model.Movie;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movie/")
public class MovieController {
    private final MovieHanlder movieHanlder;

    public MovieController(MovieHanlder movieHanlder) {
        this.movieHanlder = movieHanlder;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void createMovie(@RequestBody MovieCreateDTO movieCreateDTO){
        this.movieHanlder.handle(movieCreateDTO);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public MovieAllDTO getRandomMovie(){
        return this.movieHanlder.handle();
    }



    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<MovieAllDTO> getAllMovie(@RequestParam(required = false) String sortBy,@RequestParam(required = false) List<String> name
                                         ,@RequestParam(required = false) List<String> type,@RequestParam(required = false) List<String> genre,
                                         @RequestParam(required = false) List<String> score){
        return this.movieHanlder.handle(sortBy,name,type, genre,score);

    }

    @GetMapping("{name}")
    @ResponseStatus(HttpStatus.OK)
    public MovieObjectIDTO getObjectIdNoExisting(@PathVariable("name") String name){
        return this.movieHanlder.handle(name);
    }


}
