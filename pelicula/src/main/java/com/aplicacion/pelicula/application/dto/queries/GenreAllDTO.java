package com.aplicacion.pelicula.application.dto.queries;

import com.aplicacion.pelicula.domain.model.Genre;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GenreAllDTO {
    private  List<Genre> genres;
}
