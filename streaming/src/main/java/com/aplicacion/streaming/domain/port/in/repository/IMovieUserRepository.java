package com.aplicacion.streaming.domain.port.in.repository;

import com.aplicacion.streaming.domain.model.MovieUser;

public interface IMovieUserRepository {

    void createReview(MovieUser user);

    MovieUser getMovieReview(MovieUser user);






}
