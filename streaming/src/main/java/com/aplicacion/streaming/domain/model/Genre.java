package com.aplicacion.streaming.domain.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Genre {

    private Long id;
    private String name;
    private String idObject;
    private List<Movie> movieEntities;
}
