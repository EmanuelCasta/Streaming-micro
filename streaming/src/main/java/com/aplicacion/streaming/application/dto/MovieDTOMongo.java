package com.aplicacion.streaming.application.dto;

import com.aplicacion.streaming.domain.valueObject.MovieType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MovieDTOMongo {
    private String id;
    private Long postgrest_id;

    private String name;
    private String idGenre;
    private MovieType type;
    private Long noViewers ;
    private Long score;
    private Long qualifiedPerson;
}
