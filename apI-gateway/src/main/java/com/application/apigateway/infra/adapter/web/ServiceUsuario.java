package com.application.apigateway.infra.adapter.web;

import com.application.apigateway.application.dto.LoginDTO;
import com.application.apigateway.application.dto.ReponseDTO;
import com.application.apigateway.domain.port.IServiceUsuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.time.Duration;
import java.util.Base64;


public class ServiceUsuario implements IServiceUsuario {
    private final WebClient.Builder webClient;
    private static final String ALGORITHM = "AES";
    private static final String KEY = "UK8Z09KfAlAhm3DmeHrCD8TVhZiiogZkPCYfXGnI3qc=";
    private static final byte[] decodedKey = Base64.getDecoder().decode(KEY);


    @Value("${external.service.url.user}")
    private String urlServiceUser;

    public ServiceUsuario(WebClient.Builder webClient) {
        this.webClient = webClient;
    }

    @Override
    public Mono<ReponseDTO> openSystem(String password, String email) {
        LoginDTO loginDTO = null;
        try {
            loginDTO = LoginDTO.builder().correo(email).password(encrypt(password)).build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return this.webClient.build().post()
                .uri(urlServiceUser + "access")
                .bodyValue(loginDTO)
                .retrieve()
                .bodyToMono(ReponseDTO.class);
    }

    public String encrypt(String value) throws Exception {
        SecretKeySpec keySpec = new SecretKeySpec(decodedKey, ALGORITHM);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, keySpec);
        byte[] encrypted = cipher.doFinal(value.getBytes());
        return Base64.getEncoder().encodeToString(encrypted);
    }
}
