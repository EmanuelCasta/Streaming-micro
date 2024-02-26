package com.aplicacion.streaming.domain.service;

import com.aplicacion.streaming.domain.model.Genre;
import com.aplicacion.streaming.domain.model.Movie;
import com.aplicacion.streaming.domain.port.in.dao.IGenreDAO;
import com.aplicacion.streaming.domain.port.in.repository.IGenreRepository;
import com.aplicacion.streaming.domain.port.in.repository.IMovieRepository;
import com.aplicacion.streaming.domain.port.in.services.IMovieService;
import com.aplicacion.streaming.domain.port.out.IMovieApplicationServer;
import reactor.core.publisher.Mono;

import java.util.*;


public class MovieService implements IMovieService {

    private final IMovieRepository iMovieRepository;

    private final IMovieApplicationServer iMovieApplicationServer;

    public MovieService(IMovieRepository iMovieRepository,IMovieApplicationServer iMovieApplicationServer){
        this.iMovieRepository= iMovieRepository;
        this.iMovieApplicationServer = iMovieApplicationServer;
    }


    @Override
    public Mono<Void> createServiceMovie(Movie movie) {
        return this.iMovieApplicationServer.getExistence(movie.getName().toLowerCase())
                .flatMap(movieObtain -> {
                    if (movieObtain != null && !movieObtain.isExisting()) {
                        movie.setObjectId(movieObtain.getObjectId());
                        return Mono.fromCallable(() -> this.iMovieRepository.createMovie(movie))
                                .flatMap(createdMovie -> Mono.fromRunnable(() -> this.iMovieApplicationServer.createMovieMongo(createdMovie)))
                                .then();
                    }
                    return Mono.empty();
                })
                .then();
    }







}
