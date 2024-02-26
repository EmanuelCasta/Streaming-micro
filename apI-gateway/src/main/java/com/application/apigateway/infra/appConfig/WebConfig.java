package com.application.apigateway.infra.appConfig;

import com.application.apigateway.domain.port.IServiceUsuario;
import com.application.apigateway.infra.adapter.web.ServiceUsuario;
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
    IServiceUsuario iServiceUsuario(WebClient.Builder webClient){
        return new ServiceUsuario(webClient);
    }



}
