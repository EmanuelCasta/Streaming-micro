package com.aplicacion.pelicula.infra.genre.adapter.repository;

import com.aplicacion.pelicula.infra.genre.entity.GenreEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface IGenreMongoRepository extends MongoRepository<GenreEntity,String> {

    Optional<GenreEntity> findByName(String name);

}
