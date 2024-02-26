package com.aplicacion.pelicula.infra.genre.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "genres")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class GenreEntity {
    @Id
    private String id;

    @Indexed(unique = true)
    private String name;
}
