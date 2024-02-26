package com.aplicacion.streaming.infra.movieUser.adapter.repository;

import com.aplicacion.streaming.infra.movieUser.entity.MovieUserEntity;
import com.aplicacion.streaming.infra.movieUser.entity.MovieUserId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IMovieUserMongoRepository extends JpaRepository<MovieUserEntity, MovieUserId> {
}
