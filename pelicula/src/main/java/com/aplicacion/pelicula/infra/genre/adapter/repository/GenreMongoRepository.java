package com.aplicacion.pelicula.infra.genre.adapter.repository;


import com.aplicacion.pelicula.domain.model.Genre;
import com.aplicacion.pelicula.domain.port.in.repository.IGenreRepository;
import com.aplicacion.pelicula.infra.genre.builder.GenreBuilder;
import com.aplicacion.pelicula.infra.genre.entity.GenreEntity;
import com.aplicacion.pelicula.infra.genre.exeptionBase.CustomDuplicatedGenreException;
import com.mongodb.DuplicateKeyException;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class GenreMongoRepository implements IGenreRepository {

    private final  IGenreMongoRepository iGenreMongoRepository;

    public GenreMongoRepository(IGenreMongoRepository iGenreMongoRepository) {
        this.iGenreMongoRepository = iGenreMongoRepository;
    }

    public void createGenre(Genre genre){
        try {
            this.iGenreMongoRepository.save(GenreBuilder.convertEntity(genre));
        }catch (DuplicateKeyException e) {
                throw new CustomDuplicatedGenreException("Ya existe una genero con el mismo nombre");
        }
    }

    @Override
    public Genre getGenteById(Genre genre) {
        Optional<GenreEntity> genreEntity = this.iGenreMongoRepository.findById(genre.getId());
        if(genreEntity.isPresent()){
            return GenreBuilder.convertObject(genreEntity.get());
        }
        return Genre.builder().build();

    }
}
