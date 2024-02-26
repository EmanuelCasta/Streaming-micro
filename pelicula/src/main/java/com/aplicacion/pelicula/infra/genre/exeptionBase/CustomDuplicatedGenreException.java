package com.aplicacion.pelicula.infra.genre.exeptionBase;

public class CustomDuplicatedGenreException extends RuntimeException{
    public CustomDuplicatedGenreException(String message){
        super(message);
    }
}
