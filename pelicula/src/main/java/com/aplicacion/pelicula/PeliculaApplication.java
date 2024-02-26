package com.aplicacion.pelicula;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class PeliculaApplication {

	public static void main(String[] args) {
		SpringApplication.run(PeliculaApplication.class, args);
	}

}
