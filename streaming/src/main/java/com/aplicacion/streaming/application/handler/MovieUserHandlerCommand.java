package com.aplicacion.streaming.application.handler;


import com.aplicacion.streaming.application.dto.command.MovieUserDTOCommand;
import com.aplicacion.streaming.application.factory.MovieUserFactory;
import com.aplicacion.streaming.domain.port.in.services.IMovieUserService;
import com.aplicacion.streaming.domain.port.out.IMovieApplicationServer;
import com.aplicacion.streaming.infra.movieUser.adapter.event.MovieCreatedEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component

public class MovieUserHandlerCommand {

    private final  IMovieUserService iMovieUserService;
    private final ApplicationEventPublisher eventPublisher;


    public MovieUserHandlerCommand(IMovieUserService iMovieUserService,ApplicationEventPublisher eventPublisher) {
        this.iMovieUserService = iMovieUserService;
        this.eventPublisher = eventPublisher;
    }


    @Transactional
    public void handle(MovieUserDTOCommand userDTOCommand,Boolean esCreate){
        if(!esCreate){
            this.updateMovie(userDTOCommand);
        }else {
            this.createMovie(userDTOCommand);
        }
    }

    public void createMovie(MovieUserDTOCommand userDTOCommand){
        this.iMovieUserService.createReview(MovieUserFactory.converObject(userDTOCommand));


    }

    public void updateMovie(MovieUserDTOCommand userDTOCommand){
        this.iMovieUserService.updateReview(MovieUserFactory.converObject(userDTOCommand),userDTOCommand.getEsClickVideo());
        eventPublisher.publishEvent(new MovieCreatedEvent(this, userDTOCommand.getMovieid()));

    }
}
