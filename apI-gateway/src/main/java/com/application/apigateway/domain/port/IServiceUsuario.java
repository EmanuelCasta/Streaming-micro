package com.application.apigateway.domain.port;


import com.application.apigateway.application.dto.ReponseDTO;
import reactor.core.publisher.Mono;



public interface IServiceUsuario {

    Mono<ReponseDTO> openSystem(String password , String email);
}
