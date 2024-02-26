package com.aplicacion.streaming.infra.movieUser.controller;

import com.aplicacion.streaming.application.dto.command.MovieUserDTOCommand;
import com.aplicacion.streaming.application.handler.MovieUserHandlerCommand;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/movieuser/")
public class MovieUserController {

    private final MovieUserHandlerCommand movieUserHandlerCommand;

    private final static Boolean ES_CREATE = true;

    public MovieUserController(MovieUserHandlerCommand movieUserHandlerCommand) {
        this.movieUserHandlerCommand = movieUserHandlerCommand;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createReviewUser(@RequestBody MovieUserDTOCommand movieUserDTOCommand){
        this.movieUserHandlerCommand.handle(movieUserDTOCommand,ES_CREATE);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateReviewUser(@RequestBody MovieUserDTOCommand movieUserDTOCommand){
        this.movieUserHandlerCommand.handle(movieUserDTOCommand,!ES_CREATE);

    }
}
