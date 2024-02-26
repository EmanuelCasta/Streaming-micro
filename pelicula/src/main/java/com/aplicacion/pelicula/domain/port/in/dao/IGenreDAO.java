package com.aplicacion.pelicula.domain.port.in.dao;

import com.aplicacion.pelicula.domain.model.Genre;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface IGenreDAO {

    List<Genre> getAllGenre(Sort sort );
}
