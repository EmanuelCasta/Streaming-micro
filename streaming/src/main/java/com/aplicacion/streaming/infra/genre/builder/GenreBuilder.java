package com.aplicacion.streaming.infra.genre.builder;

import com.aplicacion.streaming.domain.model.Genre;
import com.aplicacion.streaming.infra.genre.entity.GenreEntity;

public class GenreBuilder {
    private GenreBuilder(){throw new IllegalArgumentException("Utility class");}

    public static Genre convertModel(GenreEntity genreEntity){
        return Genre.builder()
                .id(genreEntity.getId())
                .name(genreEntity.getName())
                .idObject(genreEntity.getIdObject())
                .build();
    }
    public static GenreEntity convertEntity(Genre genre) {
        if (genre == null) {
            return null;
        }
        Long genre_id = genre.getId();
        return GenreEntity.builder()
                .id(genre_id == null ? null:genre_id)
                .name(genre.getName())
                .idObject(genre.getIdObject())
                .build();
    }

}
