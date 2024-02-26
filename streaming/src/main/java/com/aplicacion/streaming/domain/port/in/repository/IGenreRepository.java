package com.aplicacion.streaming.domain.port.in.repository;

import com.aplicacion.streaming.domain.model.Genre;

public interface IGenreRepository {
    void createGenre(Genre genre);

    Genre getGenteById(Genre genre);
}
