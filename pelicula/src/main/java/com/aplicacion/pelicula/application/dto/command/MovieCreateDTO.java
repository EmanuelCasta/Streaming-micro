package com.aplicacion.pelicula.application.dto.command;

import com.aplicacion.pelicula.domain.valueObject.MovieType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MovieCreateDTO {
    private String name;
    private String idGenre;
    private MovieType type;
}
