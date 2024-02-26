package com.aplicacion.streaming.domain.port.in.repository;

import com.aplicacion.streaming.domain.model.Movie;
import org.springframework.data.domain.Sort;
import reactor.core.publisher.Mono;

import java.util.List;

public interface IMovieRepository {
    Movie  createMovie(Movie movie);

    void sumView(Long movie_id);

    void changeScore(Long movie_id,Long oldScore,Long newScore);

    void sumQualifer(Long movie_id);

    void lessQualider(Long movie_id);


}
