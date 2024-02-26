package com.aplicacion.pelicula.infra.genre.adapter.dao;

import com.aplicacion.pelicula.domain.model.Genre;
import com.aplicacion.pelicula.domain.port.in.dao.IGenreDAO;
import com.aplicacion.pelicula.infra.genre.adapter.repository.IGenreMongoRepository;
import com.aplicacion.pelicula.infra.genre.builder.GenreBuilder;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class GenreDAO implements IGenreDAO {
    private final IGenreMongoRepository iGenreMongoRepository;

    public GenreDAO(IGenreMongoRepository iGenreMongoRepository) {
        this.iGenreMongoRepository = iGenreMongoRepository;
    }

    @Override
    public List<Genre> getAllGenre(Sort sort) {
        return this.iGenreMongoRepository.findAll(sort).stream().map(GenreBuilder::convertObject).collect(Collectors.toList());
    }
}
