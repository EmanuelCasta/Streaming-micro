package com.aplicacion.streaming.infra.movieUser.adapter.event;

import com.aplicacion.streaming.domain.port.out.IMovieApplicationServer;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class MovieEventHandler {


    private final IMovieApplicationServer iMovieApplicationServer;

    public MovieEventHandler(IMovieApplicationServer iMovieApplicationServer) {
        this.iMovieApplicationServer = iMovieApplicationServer;
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void onMovieCreated(MovieCreatedEvent event) {
        this.iMovieApplicationServer.createMovieMongo(event.getMovieId());
    }

}
