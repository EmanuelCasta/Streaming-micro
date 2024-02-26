package com.aplicacion.streaming.infra.movieUser.builder;

import com.aplicacion.streaming.domain.model.MovieUser;
import com.aplicacion.streaming.infra.movie.builder.MovieBuilder;
import com.aplicacion.streaming.infra.movieUser.entity.MovieUserEntity;
import com.aplicacion.streaming.infra.movieUser.entity.MovieUserId;
import com.aplicacion.streaming.infra.user.builder.UserBuilder;

public class MovieUserBuilder {
    private MovieUserBuilder(){throw new IllegalArgumentException("Utility class");}

    public static MovieUser convertModel(MovieUserEntity movieUserEntity){
        return MovieUser.builder()
                .user(UserBuilder.convertModel(movieUserEntity.getUserEntity()))
                .movie(MovieBuilder.convertModel(movieUserEntity.getMovieEntity()))
                .score(movieUserEntity.getScore())
                .id(MovieUserId.builder().movieId(movieUserEntity.getId().getMovieId()).userId(movieUserEntity.getId().getUserId()).build())
                .noViewViews(movieUserEntity.getNoViewViews())
                .esGusto(movieUserEntity.getEsGusto())
                .esVista(movieUserEntity.getEsVista())
                .build();
    }

    public static MovieUserEntity convertEntity(MovieUser movieUser) {
        MovieUserEntity userEntity = MovieUserEntity.builder()
                .userEntity(UserBuilder.convertEntity(movieUser.getUser()))
                .movieEntity(MovieBuilder.convertEntity(movieUser.getMovie()))
                .score(movieUser.getScore())
                .id(convertEntity(movieUser.getUser().getId()
                        ,movieUser.getMovie().getId()))
                .noViewViews(movieUser.getNoViewViews())
                .esGusto(movieUser.getEsGusto())
                .esVista(movieUser.getEsVista())
                .build();

        return userEntity;
    }

    public static MovieUserId convertEntity(Long userid,Long movieId){
        return MovieUserId.builder().userId(userid).movieId(movieId).build();

    }

}
