package com.aplicacion.streaming.infra.movie.controller;


import com.aplicacion.streaming.application.dto.command.MovieDTOCommand;
import com.aplicacion.streaming.application.handler.MovieHandlerCommand;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/movie/")
public class MovieController {
    private MovieHandlerCommand movieHandlerCommand;

    public MovieController(MovieHandlerCommand movieHandlerCommand) {
        this.movieHandlerCommand = movieHandlerCommand;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Void> createMovie(@RequestBody MovieDTOCommand movieDTOCommand) {
         return this.movieHandlerCommand.handle(movieDTOCommand);
    }
}
