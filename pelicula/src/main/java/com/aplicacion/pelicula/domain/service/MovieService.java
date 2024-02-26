package com.aplicacion.pelicula.domain.service;

import com.aplicacion.pelicula.domain.model.Genre;
import com.aplicacion.pelicula.domain.model.Movie;
import com.aplicacion.pelicula.domain.port.in.dao.IGenreDAO;
import com.aplicacion.pelicula.domain.port.in.repository.IGenreRepository;
import com.aplicacion.pelicula.domain.port.in.repository.IMovieRepository;
import com.aplicacion.pelicula.domain.port.in.services.IMovieService;

import java.util.*;


public class MovieService implements IMovieService {

    private final IMovieRepository iMovieRepository;
    private final IGenreRepository iGenreRepository;

    public MovieService(IMovieRepository iMovieRepository,IGenreRepository iGenreRepository,IGenreDAO iGenreDAO){
        this.iMovieRepository= iMovieRepository;
        this.iGenreRepository = iGenreRepository;
    }


    @Override
    public void createServiceMovie(Movie movie) {
        Genre genreSearch = Genre.builder().id(movie.getIdGenre()).build();
        Genre genre = iGenreRepository.getGenteById(genreSearch);
        if(genre.getId()==null || genre.getId().isEmpty() ){
            throw new IllegalArgumentException("No existe el genero");
        }

        movie.setIdGenre(genre.getId());
        this.iMovieRepository.createMovie(movie);

    }


    @Override
    public List<Movie> getAllMovie(String sortBy, List<String> names, List<String> types, List<String> genres,List<String> scores) {
        return this.iMovieRepository.getAllWithFilter(sortBy,names,types,genres,scores);

    }

    @Override
    public Movie getRandomMovie() {
        List<Movie> moviesTop = this.iMovieRepository.getTopFiveNoViewrAscScores();
        List<Movie> movieRandom = this.iMovieRepository.getRandomTopTenMovies();
        List<Movie> combined = new ArrayList<>();
        combined.addAll(moviesTop);
        combined.addAll(movieRandom);
        Collections.shuffle(combined);
        Random random = new Random();
        return combined.get(random.nextInt(combined.size()));

    }

    @Override
    public Movie getMovie(Movie movie) {
        return this.iMovieRepository.getMovie(movie);
    }


}
