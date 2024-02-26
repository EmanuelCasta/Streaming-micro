package com.aplicacion.streaming.infra.appConfig;


import com.aplicacion.streaming.domain.port.out.IMovieApplicationServer;
import com.aplicacion.streaming.infra.movie.adapter.web.movieServiceMongo.MovieApplicationServer;
import com.aplicacion.streaming.infra.movieUser.adapter.event.MovieEventHandler;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.web.reactive.function.client.WebClient;


@Configuration
public class WebConfig {

    @Bean
    @LoadBalanced
    public WebClient.Builder webClient(){
        return WebClient.builder();
    }

    @Bean
    IMovieApplicationServer iMovieApplicationServer(WebClient.Builder webClient){
        return new MovieApplicationServer(webClient);
    }

    @Bean
    MovieEventHandler movieEventHandler(IMovieApplicationServer iMovieApplicationServer){
        return new MovieEventHandler(iMovieApplicationServer);
    }


}
