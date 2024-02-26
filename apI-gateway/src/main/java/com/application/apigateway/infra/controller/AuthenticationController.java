package com.application.apigateway.infra.controller;

import com.application.apigateway.application.dto.CredentialDTO;
import com.application.apigateway.application.dto.LoginDTO;
import com.application.apigateway.application.handler.UsuarioHandler;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/apigateway/secure/")
public class AuthenticationController {

    private UsuarioHandler usuarioHandler;

    public AuthenticationController(UsuarioHandler usuarioHandler) {
        this.usuarioHandler = usuarioHandler;
    }

    @PostMapping("login")
    public Mono<ResponseEntity<CredentialDTO>> login(@RequestBody LoginDTO request) {
        return this.usuarioHandler.handle(request)
                .map(credentialDTO -> ResponseEntity.ok(credentialDTO))
                .defaultIfEmpty(ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(CredentialDTO.builder().token("Fail").build()));
    }

}
