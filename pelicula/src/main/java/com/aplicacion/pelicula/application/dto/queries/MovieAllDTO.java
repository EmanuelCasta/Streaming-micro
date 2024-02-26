package com.aplicacion.pelicula.application.dto.queries;

import com.aplicacion.pelicula.domain.valueObject.MovieType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MovieAllDTO {
    private String id;
    private String name;
    private String idGenre;
    private MovieType type;
    private Long noViewers;
    private Long score;
    private Long postgrest_id;
    private Long qualifiedPerson;
}



