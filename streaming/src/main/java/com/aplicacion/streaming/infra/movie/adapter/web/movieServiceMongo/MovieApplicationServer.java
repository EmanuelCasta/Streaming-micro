package com.aplicacion.streaming.infra.movie.adapter.web.movieServiceMongo;

import com.aplicacion.streaming.application.dto.MovieObjectIDTO;
import com.aplicacion.streaming.application.factory.MovieFactory;
import com.aplicacion.streaming.domain.model.Movie;
import com.aplicacion.streaming.domain.port.out.IMovieApplicationServer;
import com.aplicacion.streaming.infra.movie.adapter.repository.IMoviePostgressqlRepository;
import com.aplicacion.streaming.infra.movie.builder.MovieBuilder;
import com.aplicacion.streaming.infra.movie.entity.MovieEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Optional;

public class MovieApplicationServer implements IMovieApplicationServer {
    private final WebClient.Builder webClient;

    @Autowired
    private IMoviePostgressqlRepository iMoviePostgressqlRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Value("${external.service.url.movie}")
    private String urlServiceMongoExternal;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    public MovieApplicationServer(WebClient.Builder  webClient) {
        this.webClient = webClient;
    }

    @Override
    public Mono<Movie> getExistence(String name) {
        return this.webClient.build().get()
                .uri(urlServiceMongoExternal + "{name}", name)
                .retrieve()
                .bodyToMono(MovieObjectIDTO.class)
                .timeout(Duration.ofSeconds(10))
                .map(MovieFactory::convertObject);
    }

    @Override
    @Transactional
    public void createMovieMongo(Long id) {
        Optional<MovieEntity> movieEntityOpt =  iMoviePostgressqlRepository.findById(id);

        if (movieEntityOpt.isPresent()) {
            MovieEntity movieEntity = movieEntityOpt.get();
            entityManager.refresh(movieEntity);
            Movie movie = MovieBuilder.convertModel(movieEntity);
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                String movieJson = objectMapper.writeValueAsString(MovieFactory.convertMessage(movie));
                kafkaTemplate.send("movie-topic", movieJson);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }

    }

    @Override
    @Async
    public void createMovieMongo(Movie movie) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            System.out.println("Hola");
            System.out.println(MovieFactory.convertMessage(movie));
            String movieJson =  objectMapper.writeValueAsString(MovieFactory.convertMessage(movie));
            kafkaTemplate.send("movie-topic",movieJson);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }


}
