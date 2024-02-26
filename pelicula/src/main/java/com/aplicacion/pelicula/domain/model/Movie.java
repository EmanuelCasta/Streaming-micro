package com.aplicacion.pelicula.domain.model;

import com.aplicacion.pelicula.domain.valueObject.MovieType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Movie {
    private String id;
    @Builder.Default
    private Long postgrest_id=0L;
    private String name;
    private String idGenre;
    private MovieType type;
    private Long noViewers ;
    private Long score;
    @Builder.Default
    private Long qualifiedPerson=0L;

}
