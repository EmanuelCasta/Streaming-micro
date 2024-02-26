package com.aplicacion.streaming.infra.appConfig;

import com.aplicacion.streaming.domain.port.in.repository.IMovieRepository;
import com.aplicacion.streaming.domain.port.in.repository.IMovieUserRepository;
import com.aplicacion.streaming.domain.port.in.services.IMovieService;
import com.aplicacion.streaming.domain.port.in.services.IMovieUserService;
import com.aplicacion.streaming.domain.port.out.IMovieApplicationServer;
import com.aplicacion.streaming.domain.service.MovieUserService;
import com.aplicacion.streaming.infra.movieUser.adapter.event.MovieEventHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration

public class MovieUserConfig {

    @Bean
    IMovieUserService iMovieUserService(IMovieUserRepository iMovieUserRepository, IMovieRepository iMovieRepository ){
        return new MovieUserService(iMovieUserRepository,iMovieRepository);
    }


}
