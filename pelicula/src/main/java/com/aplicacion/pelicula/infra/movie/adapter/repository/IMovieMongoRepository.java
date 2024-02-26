package com.aplicacion.pelicula.infra.movie.adapter.repository;

import com.aplicacion.pelicula.domain.model.Movie;
import com.aplicacion.pelicula.infra.movie.entity.MovieEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface IMovieMongoRepository extends MongoRepository<MovieEntity, ObjectId> {
    List<MovieEntity> findTop5ByOrderByNoViewersAscScoreDesc();

    MovieEntity findByName(String name);
}
