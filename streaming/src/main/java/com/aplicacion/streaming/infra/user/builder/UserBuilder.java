package com.aplicacion.streaming.infra.user.builder;

import com.aplicacion.streaming.domain.model.User;
import com.aplicacion.streaming.infra.movie.builder.MovieBuilder;
import com.aplicacion.streaming.infra.movieUser.builder.MovieUserBuilder;
import com.aplicacion.streaming.infra.user.entity.UserEntity;

import java.util.stream.Collectors;

public class UserBuilder {

    private UserBuilder(){
        throw new IllegalArgumentException("Utility class");
    }

    public static User convertModel(UserEntity userEntity){
        return User.builder()
                .correo(userEntity.getCorreo())
                .edad(userEntity.getEdad())
                .password(userEntity.getPassword())
                .id(userEntity.getId())
                .movieUserEntities(userEntity.getMovieUserEntities().stream().map(MovieUserBuilder::convertModel).collect(Collectors.toSet()))
                .build();
    }
    public static UserEntity convertEntity(User user) {
        return UserEntity.builder()
                .correo(user.getCorreo())
                .edad(user.getEdad())
                .password(user.getPassword())
                .id(user.getId())
                .build();
    }


}
