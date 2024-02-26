package com.aplicacion.streaming.domain.port.in.services;

import com.aplicacion.streaming.domain.model.MovieUser;

public interface IMovieUserService {

    void createReview(MovieUser movieUser);

    void updateReview(MovieUser movieUser,Boolean esClickVideo);
}
