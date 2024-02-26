package com.aplicacion.streaming.application.factory;

import com.aplicacion.streaming.application.dto.command.MovieUserDTOCommand;
import com.aplicacion.streaming.domain.model.Movie;
import com.aplicacion.streaming.domain.model.MovieUser;
import com.aplicacion.streaming.domain.model.User;

public class MovieUserFactory {
    private MovieUserFactory(){
        throw new IllegalArgumentException("Utility class");
    }


    public static MovieUser converObject(MovieUserDTOCommand userDTOCommand){
        if(userDTOCommand.getUserid()==null || userDTOCommand.getMovieid() ==null){
            throw new IllegalArgumentException("Se debe ingresar ambos valores movieid y userid");
        }
        return MovieUser.builder()
                .user(User.builder().id(userDTOCommand.getUserid()).build())
                .movie(Movie.builder().id(userDTOCommand.getMovieid()).build())
                .esVista(userDTOCommand.getEsVista())
                .esGusto(userDTOCommand.getEsGusto())
                .noViewViews(userDTOCommand.getNoViewViews())
                .score(userDTOCommand.getScore())
                .build();


    }
}
