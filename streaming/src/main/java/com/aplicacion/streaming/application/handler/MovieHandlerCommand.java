package com.aplicacion.streaming.application.handler;

import com.aplicacion.streaming.application.dto.command.MovieDTOCommand;
import com.aplicacion.streaming.application.factory.MovieFactory;
import com.aplicacion.streaming.domain.port.in.services.IMovieService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Component
@Transactional
public class MovieHandlerCommand {

    private final IMovieService iMovieService;

    public MovieHandlerCommand(IMovieService iMovieService) {
        this.iMovieService = iMovieService;
    }


    public Mono<Void> handle(MovieDTOCommand movieDTOCommand) {
        return this.iMovieService.createServiceMovie(MovieFactory.convertObject(movieDTOCommand));
    }
}
