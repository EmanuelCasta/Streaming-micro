package com.aplicacion.streaming.domain.port.out;

import com.aplicacion.streaming.domain.model.Movie;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.scheduling.annotation.Async;
import reactor.core.publisher.Mono;


public interface IMovieApplicationServer {

    Mono<Movie> getExistence(String name);

    void createMovieMongo(Movie movie);


    void createMovieMongo(Long id);
}
