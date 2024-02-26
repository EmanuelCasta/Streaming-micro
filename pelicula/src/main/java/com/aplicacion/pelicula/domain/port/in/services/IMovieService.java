package com.aplicacion.pelicula.domain.port.in.services;

import com.aplicacion.pelicula.domain.model.Movie;

import java.util.List;

public interface IMovieService {

    void createServiceMovie(Movie movie);

    List<Movie> getAllMovie(String sortBy, List<String> name, List<String> type, List<String> genre,List<String> score);

    Movie getRandomMovie();

    Movie getMovie(Movie movie);
}
