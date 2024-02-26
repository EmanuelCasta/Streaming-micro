package com.aplicacion.pelicula.infra.movie.adapter.repository;


import com.aplicacion.pelicula.domain.model.Genre;
import com.aplicacion.pelicula.domain.model.Movie;
import com.aplicacion.pelicula.domain.port.in.dao.IGenreDAO;
import com.aplicacion.pelicula.domain.port.in.repository.IMovieRepository;
import com.aplicacion.pelicula.domain.valueObject.SortType;
import com.aplicacion.pelicula.infra.movie.builder.MovieBuilder;
import com.aplicacion.pelicula.infra.movie.entity.MovieEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Repository
public class MovieMongoRepository implements IMovieRepository {

    private final IMovieMongoRepository iMovieMongoRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    private IGenreDAO iGenreDAO;

    public  MovieMongoRepository(IMovieMongoRepository iMovieMongoRepository,IGenreDAO iGenreDAO){
        this.iMovieMongoRepository = iMovieMongoRepository;
        this.iGenreDAO= iGenreDAO;
    }

    public void createMovie(Movie movie){
        this.iMovieMongoRepository.save(MovieBuilder.convertEntity(movie));
    }

    @Override
    public List<Movie> getAllWithFilter(String sortBy,List<String> names, List<String> types, List<String> genres,List<String> scores){
        Query query= MovieBuilder.convertQuery(names,types,genres,scores);

        if (sortBy != null) {
            SortType sortType = SortType.valueOf(sortBy);
            switch (sortType) {
                case name:
                case score:
                case type:
                    query.with(Sort.by(Sort.Direction.ASC, sortBy));
                    break;
                case idGenre:
                    List<Genre> genresList = this.iGenreDAO.getAllGenre(Sort.by(Sort.Direction.ASC, "id"));
                    Map<String, Integer> genreOrder = IntStream.range(0, genresList.size())
                            .boxed()
                            .collect(Collectors.toMap(i -> genresList.get(i).getId(), i -> i));
                    List<Movie> movies = mongoTemplate.find(query, MovieEntity.class).stream().map(MovieBuilder::convertModel).toList();
                    movies.sort(Comparator.comparingInt(movie -> genreOrder.getOrDefault(movie.getIdGenre(), Integer.MAX_VALUE)));
                    return movies;
            }
        }
        return mongoTemplate.find(query, MovieEntity.class).stream().map(MovieBuilder::convertModel).toList();

    }

    @Override
    public List<Movie> getTopFiveNoViewrAscScores() {
        return this.iMovieMongoRepository.findTop5ByOrderByNoViewersAscScoreDesc().stream().map(MovieBuilder::convertModel).toList();
    }

    @Override
    public List<Movie> getRandomTopTenMovies(){
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.sample(10)
        );
        return mongoTemplate.aggregate(aggregation,MovieEntity.class, MovieEntity.class).getMappedResults().stream().map(MovieBuilder::convertModel).toList();
    }

    @Override
    public Movie getMovie(Movie movie) {
        return MovieBuilder.convertModel(this.iMovieMongoRepository.findByName(movie.getName()));
    }
}
