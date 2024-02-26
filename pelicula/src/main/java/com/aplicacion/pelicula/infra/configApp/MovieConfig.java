package com.aplicacion.pelicula.infra.configApp;

import com.aplicacion.pelicula.application.handleCommand.MovieHanlder;
import com.aplicacion.pelicula.domain.port.in.dao.IGenreDAO;
import com.aplicacion.pelicula.domain.port.in.repository.IGenreRepository;
import com.aplicacion.pelicula.domain.port.in.repository.IMovieRepository;
import com.aplicacion.pelicula.domain.port.in.services.IMovieService;
import com.aplicacion.pelicula.domain.service.MovieService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MovieConfig {
    @Bean
    IMovieService iMovieService(IMovieRepository iMovieRepository, IGenreRepository iGenreRepository,IGenreDAO iGenreDAO){
        return new MovieService(iMovieRepository,iGenreRepository, iGenreDAO);
    }

    @Bean
    MovieHanlder movieHanlderCommand(IMovieService iMovieService){
        return new MovieHanlder(iMovieService);
    }
}
