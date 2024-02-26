package com.aplicacion.pelicula.infra.configApp;

import com.aplicacion.pelicula.application.handleCommand.GenreHanlder;
import com.aplicacion.pelicula.domain.port.in.dao.IGenreDAO;
import com.aplicacion.pelicula.domain.port.in.repository.IGenreRepository;
import com.aplicacion.pelicula.domain.port.in.services.IGenreService;
import com.aplicacion.pelicula.domain.service.GenreService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GenreConfig {
    @Bean
    IGenreService iGenreService(IGenreRepository iGenreRepository, IGenreDAO iGenreDAO){
        return new GenreService(iGenreRepository,iGenreDAO);
    }

    @Bean
    GenreHanlder genreHanlderCommand(IGenreService iGenreService){
        return  new GenreHanlder(iGenreService);
    }
}
