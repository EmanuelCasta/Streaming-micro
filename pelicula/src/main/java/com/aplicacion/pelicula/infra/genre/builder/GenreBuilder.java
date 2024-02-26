package com.aplicacion.pelicula.infra.genre.builder;

import com.aplicacion.pelicula.domain.model.Genre;
import com.aplicacion.pelicula.infra.genre.entity.GenreEntity;

public class GenreBuilder {
    private GenreBuilder(){throw new IllegalStateException("Utility class");}

    public static GenreEntity convertEntity(Genre genre){
        return GenreEntity.builder()
                .name(genre.getName())
                .build();
    }

    public static Genre convertObject(GenreEntity genre){
        return Genre.builder()
                .id(genre.getId())
                .name(genre.getName())
                .build();

    }

}
