package com.aplicacion.streaming.infra.movie.adapter.repository;

import com.aplicacion.streaming.domain.model.Movie;
import com.aplicacion.streaming.domain.port.in.repository.IMovieRepository;
import com.aplicacion.streaming.infra.movie.builder.MovieBuilder;
import org.springframework.stereotype.Repository;

@Repository
public class MoviePostgressqlRepository implements IMovieRepository {

    private IMoviePostgressqlRepository iMoviePostgressqlRepository;

    public MoviePostgressqlRepository(IMoviePostgressqlRepository iMoviePostgressqlRepository) {
        this.iMoviePostgressqlRepository = iMoviePostgressqlRepository;
    }

    @Override
    public Movie createMovie(Movie movie) {
        return MovieBuilder.convertModel(this.iMoviePostgressqlRepository.save(MovieBuilder.convertEntity(movie)));


    }

    @Override
    public void sumView(Long id) {
        this.iMoviePostgressqlRepository.sumViewMovie(id);
    }

    @Override
    public void changeScore(Long movie_id, Long oldScore, Long newScore) {
        this.iMoviePostgressqlRepository.changeScore(movie_id,oldScore,newScore);
    }

    @Override
    public void sumQualifer(Long movie_id) {
        this.iMoviePostgressqlRepository.sumQualifer(movie_id);
    }

    @Override
    public void lessQualider(Long movie_id) {
        this.iMoviePostgressqlRepository.lessQualifer(movie_id);

    }


}
