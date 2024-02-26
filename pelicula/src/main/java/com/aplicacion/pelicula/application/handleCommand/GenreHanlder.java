package com.aplicacion.pelicula.application.handleCommand;

import com.aplicacion.pelicula.application.dto.command.GenreCreateDTO;
import com.aplicacion.pelicula.application.dto.queries.GenreAllDTO;
import com.aplicacion.pelicula.application.factory.GenreFactory;
import com.aplicacion.pelicula.domain.model.Genre;
import com.aplicacion.pelicula.domain.port.in.services.IGenreService;
import com.aplicacion.pelicula.infra.genre.builder.GenreBuilder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class GenreHanlder {
    private final IGenreService iGenreService;

    public GenreHanlder(IGenreService iGenreService) {
        this.iGenreService = iGenreService;
    }

    @Transactional
    public void handle(GenreCreateDTO genreCreateDTO){
        Genre genre = GenreFactory.generateGenre(genreCreateDTO);
        this.iGenreService.createGenre(genre);
    }

    @Transactional(readOnly = true)
    public GenreAllDTO handle(){
        return GenreFactory.generateAllGenre(this.iGenreService.getAllGenre( ));

    }

}
