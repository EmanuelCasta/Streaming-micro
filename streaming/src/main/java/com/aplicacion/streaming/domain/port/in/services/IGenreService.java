package com.aplicacion.streaming.domain.port.in.services;

import com.aplicacion.streaming.domain.model.Genre;

import java.util.List;

public interface IGenreService {

    void createGenre(Genre genre);

    List<Genre>  getAllGenre();
}
