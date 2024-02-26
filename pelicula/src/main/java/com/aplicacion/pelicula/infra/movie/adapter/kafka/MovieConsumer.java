package com.aplicacion.pelicula.infra.movie.adapter.kafka;

import com.aplicacion.pelicula.domain.model.Movie;
import com.aplicacion.pelicula.infra.movie.adapter.repository.IMovieMongoRepository;
import com.aplicacion.pelicula.infra.movie.builder.MovieBuilder;
import com.aplicacion.pelicula.infra.movie.entity.MovieEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class MovieConsumer {
    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private IMovieMongoRepository iMovieMongoRepository;
    @KafkaListener(topics = "movie-topic")
    public void listenProduct(Movie movie) {

        MovieEntity movieEntity = MovieBuilder.convertEntity(movie);

        try {

            System.out.println(movieEntity);
            iMovieMongoRepository.save(MovieBuilder.convertEntity(movie));


        } catch (Exception e) {
            System.err.println("Error inesperado al guardar la película: " + e.getMessage());
        }
    }
}
