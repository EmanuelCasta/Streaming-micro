package com.aplicacion.pelicula.domain.port.in.repository;

import com.aplicacion.pelicula.domain.model.Genre;

public interface IGenreRepository {
    void createGenre(Genre genre);

    Genre getGenteById(Genre genre);
}
