package com.aplicacion.pelicula.domain.port.in.repository;

import com.aplicacion.pelicula.domain.model.Movie;

import java.util.List;

public interface IMovieRepository {
    void createMovie(Movie movie);

    List<Movie> getAllWithFilter(String sortBy,List<String> names, List<String> types, List<String> genres,List<String> scores);

    List<Movie> getTopFiveNoViewrAscScores();

    List<Movie> getRandomTopTenMovies();

    Movie getMovie(Movie movie);


}
