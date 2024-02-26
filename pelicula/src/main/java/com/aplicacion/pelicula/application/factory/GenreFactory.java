package com.aplicacion.pelicula.application.factory;

import com.aplicacion.pelicula.application.dto.command.GenreCreateDTO;
import com.aplicacion.pelicula.application.dto.queries.GenreAllDTO;
import com.aplicacion.pelicula.domain.model.Genre;

import java.util.List;

public class GenreFactory {
    private GenreFactory() {
        throw new IllegalStateException("Utility class");
    }

    public static Genre generateGenre(GenreCreateDTO genreCreateDTO){
        String name = genreCreateDTO.getName().toLowerCase();
        return Genre.builder()
                .name(name)
                .build();
    }

    public static GenreAllDTO generateAllGenre(List<Genre> genres){
        return GenreAllDTO.builder()
                .genres(genres)
                .build();
    }
}
