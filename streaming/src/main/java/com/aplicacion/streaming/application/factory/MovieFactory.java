package com.aplicacion.streaming.application.factory;

import com.aplicacion.streaming.application.dto.MovieDTOMongo;
import com.aplicacion.streaming.application.dto.MovieObjectIDTO;
import com.aplicacion.streaming.application.dto.command.MovieDTOCommand;
import com.aplicacion.streaming.domain.model.Genre;
import com.aplicacion.streaming.domain.model.Movie;

public class MovieFactory {

    private MovieFactory(){throw new IllegalArgumentException("Utility class");}

    public static  Movie convertObject(MovieDTOCommand movieDTOCommand){
        return Movie.builder()
                .genre(Genre.builder().id(movieDTOCommand.getGenre_id()).build())
                .name(movieDTOCommand.getName().toLowerCase())
                .type(movieDTOCommand.getType())
                .build();
    }

    public static Movie convertObject(MovieObjectIDTO movieObjectIDTO){
        return Movie.builder()
                .objectId(movieObjectIDTO.getIdObject())
                .isExisting(movieObjectIDTO.getIsExiting())
                .build();
    }

    public static MovieDTOMongo convertMessage(Movie movie){
        return MovieDTOMongo.builder()
                .id(movie.getObjectId())
                .postgrest_id(movie.getId())
                .score(movie.getScore())
                .idGenre("65db9ee32263515075801e48")
                .name(movie.getName())
                .noViewers(movie.getNoViewers())
                .qualifiedPerson(movie.getQualifiedPerson())
                .type(movie.getType())
                .build();

    }
}
