package com.aplicacion.streaming.domain.port.in.services;

import com.aplicacion.streaming.domain.model.Movie;
import reactor.core.publisher.Mono;

import java.util.List;

public interface IMovieService {

    Mono<Void> createServiceMovie(Movie movie);


}
