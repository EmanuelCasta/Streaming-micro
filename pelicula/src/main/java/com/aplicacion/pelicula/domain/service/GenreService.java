package com.aplicacion.pelicula.domain.service;

import com.aplicacion.pelicula.domain.model.Genre;
import com.aplicacion.pelicula.domain.port.in.dao.IGenreDAO;
import com.aplicacion.pelicula.domain.port.in.repository.IGenreRepository;
import com.aplicacion.pelicula.domain.port.in.services.IGenreService;
import org.springframework.data.domain.Sort;

import java.util.List;

public class GenreService implements IGenreService {

    private final IGenreRepository iGenreRepository;
    private final IGenreDAO  iGenreDAO;

    public GenreService(IGenreRepository iGenreRepository,IGenreDAO  iGenreDAO) {
        this.iGenreRepository = iGenreRepository;
        this.iGenreDAO =  iGenreDAO;
    }

    @Override
    public void createGenre(Genre genre) {
        this.iGenreRepository.createGenre(genre);
    }

    @Override
    public List<Genre> getAllGenre() {
        Sort sort = Sort.unsorted();
        return this.iGenreDAO.getAllGenre(sort);
    }


}
