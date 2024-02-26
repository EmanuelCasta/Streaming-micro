package com.aplicacion.streaming.infra.movieUser.adapter.event;

import org.springframework.context.ApplicationEvent;
public class MovieCreatedEvent extends ApplicationEvent {
    private final Long movieId;

    public MovieCreatedEvent(Object source, Long movieId) {
        super(source);
        this.movieId = movieId;
    }
    public Long getMovieId() {
        return movieId;
    }
}
