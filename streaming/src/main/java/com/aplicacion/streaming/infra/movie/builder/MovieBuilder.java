package com.aplicacion.streaming.infra.movie.builder;

import com.aplicacion.streaming.domain.model.Movie;
import com.aplicacion.streaming.domain.model.MovieUser;
import com.aplicacion.streaming.infra.genre.builder.GenreBuilder;
import com.aplicacion.streaming.infra.movie.entity.MovieEntity;
import com.aplicacion.streaming.infra.movieUser.builder.MovieUserBuilder;

import java.util.Set;
import java.util.stream.Collectors;

public class MovieBuilder {
    private MovieBuilder(){throw new IllegalArgumentException("Utility class");}

    public static Movie convertModel(MovieEntity movieEntity){
        Set<MovieUser> movieUserSet =null;
        if(movieEntity.getMovieUserEntities() != null){
            movieUserSet = movieEntity.getMovieUserEntities().stream().map(MovieUserBuilder::convertModel).collect(Collectors.toSet());

        }
        return Movie.builder()
                .id(movieEntity.getId())
                .name(movieEntity.getName())
                .objectId(movieEntity.getObjectId()!=null?movieEntity.getObjectId():null)
                .score(movieEntity.getScore())
                .type(movieEntity.getType())
                .noViewers(movieEntity.getNoViewers())
                .qualifiedPerson(movieEntity.getQualifiedPerson())
                .genre(GenreBuilder.convertModel(movieEntity.getGenre()))
                .movieUserEntities(movieUserSet)
                .build();
    }
    public static MovieEntity convertEntity(Movie movie) {
        return MovieEntity.builder()
                .id(movie.getId())
                .objectId(movie.getObjectId())
                .name(movie.getName())
                .score(movie.getScore())
                .type(movie.getType())
                .noViewers(movie.getNoViewers())
                .genre(GenreBuilder.convertEntity(movie.getGenre()))
                .build();
    }







}
