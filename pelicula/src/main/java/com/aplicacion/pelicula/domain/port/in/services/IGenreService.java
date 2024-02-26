package com.aplicacion.pelicula.domain.port.in.services;

import com.aplicacion.pelicula.domain.model.Genre;

import java.util.List;

public interface IGenreService {

    void createGenre(Genre genre);

    List<Genre>  getAllGenre();
}
