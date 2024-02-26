package com.application.apigateway.application.handler;

import com.application.apigateway.application.dto.CredentialDTO;
import com.application.apigateway.application.dto.LoginDTO;
import com.application.apigateway.domain.port.IServiceUsuario;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class UsuarioHandler {
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    private final IServiceUsuario iServiceUsuario;
    @Value("${jwt.secret}")
    private String jwtSecret;

    public UsuarioHandler(IServiceUsuario iServiceUsuario) {
        this.iServiceUsuario = iServiceUsuario;
    }

    public Mono<CredentialDTO> handle(LoginDTO loginDTO) {

        return this.iServiceUsuario.openSystem(loginDTO.getPassword(), loginDTO.getCorreo())
                .map(reponseDTO -> {
                    if (reponseDTO!= null && reponseDTO.getIsAuthent()) {
                        return CredentialDTO.builder()
                                .token(Jwts.builder()
                                        .setSubject(loginDTO.getCorreo())
                                        .signWith(SignatureAlgorithm.HS512, jwtSecret)
                                        .compact())
                                .id(reponseDTO.getId())
                                .build();
                    } else {
                        return CredentialDTO.builder().token("Fail").build();
                    }
                });
    }
}
