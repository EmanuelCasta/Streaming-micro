package com.aplicacion.pelicula.application.factory;

import com.aplicacion.pelicula.application.dto.command.MovieCreateDTO;
import com.aplicacion.pelicula.application.dto.queries.MovieAllDTO;
import com.aplicacion.pelicula.application.dto.queries.MovieObjectIDTO;
import com.aplicacion.pelicula.domain.model.Movie;

public class MovieFactory {

    private MovieFactory(){throw new IllegalStateException("Utility class");}

    public static Movie generateMovie(MovieCreateDTO movieCreateDTO){
        String nameMovie = movieCreateDTO.getName().toLowerCase();
        return Movie.builder()
                .name(nameMovie)
                .type(movieCreateDTO.getType())
                .idGenre(movieCreateDTO.getIdGenre())
                .build();
    }

    public static MovieAllDTO generateDTOMovie(Movie movie){
        return  MovieAllDTO.builder()
                .qualifiedPerson(movie.getQualifiedPerson())
                .postgrest_id(movie.getPostgrest_id())
                .noViewers(movie.getNoViewers())
                .score(movie.getScore())
                .type(movie.getType())
                .name(movie.getName())
                .idGenre(movie.getIdGenre())
                .id(movie.getId())
                .build();
    }

    public static Movie generateMovie(String name){
        return Movie.builder().name(name.toLowerCase()).build();
    }

    public static MovieObjectIDTO generateMovie(Movie movie,String objectId){
        return MovieObjectIDTO.builder()
                .idObject(objectId)
                .isExiting( movie!=null && movie.getName()!=null && !movie.getName().equals("") )
                .build();
    }

}
