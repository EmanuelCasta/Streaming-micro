package com.aplicacion.pelicula.infra.movie.builder;

import com.aplicacion.pelicula.domain.model.Movie;
import com.aplicacion.pelicula.domain.valueObject.SortType;
import com.aplicacion.pelicula.infra.movie.entity.MovieEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;
import java.util.stream.Collectors;

public class MovieBuilder {
    private MovieBuilder(){throw new IllegalStateException("Utility class");}

    public static MovieEntity convertEntity(Movie movie){
        return MovieEntity.builder()
                .id(movie.getId()!=null?new ObjectId(movie.getId()):null)
                .postgrest_id(movie.getPostgrest_id()!=null?movie.getPostgrest_id():null)
                .name(movie.getName())
                .type(movie.getType())
                .score(movie.getScore())
                .qualifiedPerson(movie.getQualifiedPerson())
                .noViewers(movie.getNoViewers())
                .idGenre(movie.getIdGenre())
                .build();
    }

    public static Movie convertModel(MovieEntity movieEntity){
        if (movieEntity == null) {
            return null;
        }
        return Movie.builder()
                .id(movieEntity.getId()!=null?movieEntity.getId().toHexString():null)
                .postgrest_id(movieEntity.getPostgrest_id())
                .idGenre(movieEntity.getIdGenre())
                .name(movieEntity.getName())
                .type(movieEntity.getType())
                .score(movieEntity.getScore())
                .qualifiedPerson(movieEntity.getQualifiedPerson())
                .noViewers(movieEntity.getNoViewers())
                .build();
    }

    public static Query convertQuery(List<String> names, List<String> types, List<String> genres, List<String> scores){
        Criteria criteria = new Criteria();
        if(names!=null && !names.isEmpty()){
            criteria = criteria.and(SortType.name.toString()).in(names);
        }
        if(types!=null && !types.isEmpty()){
            criteria = criteria.and(SortType.type.toString()).in(types);
        }
        if(genres!=null && !genres.isEmpty()){
            criteria = criteria.and(SortType.idGenre.toString()).in(genres);
        }
        if(scores!=null && !scores.isEmpty()){
            criteria = criteria.and(SortType.score.toString()).in(scores.stream().map(Long::parseLong).collect(Collectors.toList()) );
        }
        return Query.query(criteria);

    }
}
