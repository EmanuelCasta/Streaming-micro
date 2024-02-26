package com.aplicacion.pelicula.infra.movie.entity;

import com.aplicacion.pelicula.domain.valueObject.MovieType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Document(value = "movies")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class MovieEntity {

    @Id
    private ObjectId id;
    private Long postgrest_id;
    @Indexed(unique = true)
    private String name;
    private String idGenre;
    private MovieType type;
    @Builder.Default
    private Long noViewers = 0L;
    private Long score ;
    @Builder.Default
    private Long qualifiedPerson = 0L;
}









