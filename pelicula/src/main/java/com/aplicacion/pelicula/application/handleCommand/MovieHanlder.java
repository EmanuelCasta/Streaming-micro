package com.aplicacion.pelicula.application.handleCommand;

import com.aplicacion.pelicula.application.dto.command.MovieCreateDTO;
import com.aplicacion.pelicula.application.dto.queries.MovieAllDTO;
import com.aplicacion.pelicula.application.dto.queries.MovieObjectIDTO;
import com.aplicacion.pelicula.application.factory.MovieFactory;
import com.aplicacion.pelicula.domain.model.Movie;
import com.aplicacion.pelicula.domain.port.in.services.IMovieService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MovieHanlder {
    private final IMovieService iMovieService;
    public MovieHanlder(IMovieService iMovieService){
        this.iMovieService = iMovieService;
    }
    @Transactional
    public void handle(MovieCreateDTO movieCreateDTO){
        Movie movie = MovieFactory.generateMovie(movieCreateDTO);
        this.iMovieService.createServiceMovie(movie);
    }

    @Transactional(readOnly = true)
    public List<MovieAllDTO> handle(String sortBy, List<String> name, List<String> type, List<String> genre,List<String> score){
        List<Movie> movies = iMovieService.getAllMovie( sortBy,name, type, genre,score);
        return movies.stream().map(MovieFactory::generateDTOMovie).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public MovieAllDTO handle(){
        return MovieFactory.generateDTOMovie(iMovieService.getRandomMovie());
    }

    @Transactional(readOnly = true)
    public MovieObjectIDTO handle(String name){
        ObjectId objectId = new ObjectId();
        Movie movie = iMovieService.getMovie(MovieFactory.generateMovie(name));

        return MovieFactory.generateMovie(movie, objectId.toHexString());
    }
}
