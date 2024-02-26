package com.aplicacion.streaming.infra.appConfig;

import com.aplicacion.streaming.application.handler.MovieHandlerCommand;
import com.aplicacion.streaming.domain.port.in.repository.IMovieRepository;
import com.aplicacion.streaming.domain.port.in.services.IMovieService;
import com.aplicacion.streaming.domain.port.out.IMovieApplicationServer;
import com.aplicacion.streaming.domain.service.MovieService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class MovieConfig {
    @Bean
    IMovieService iMovieService(IMovieRepository iMovieRepository, IMovieApplicationServer iMovieApplicationServer){
        return new MovieService(iMovieRepository,iMovieApplicationServer);
    }

    @Bean
    MovieHandlerCommand movieHanlerCommand(IMovieService iMovieService){
        return new MovieHandlerCommand(iMovieService);
    }
}
