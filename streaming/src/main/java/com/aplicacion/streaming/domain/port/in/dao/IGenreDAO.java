package com.aplicacion.streaming.domain.port.in.dao;

import com.aplicacion.streaming.domain.model.Genre;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface IGenreDAO {

    List<Genre> getAllGenre(Sort sort );
}
