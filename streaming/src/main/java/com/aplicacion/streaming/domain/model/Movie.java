package com.aplicacion.streaming.domain.model;

import com.aplicacion.streaming.domain.valueObject.MovieType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Movie {
    private Long id;

    private String name;

    private String objectId;

    private Genre genre;

    private MovieType type;
    @Builder.Default
    private Long noViewers = 0L;
    @Builder.Default
    private Long score = 0L;

    private Set<MovieUser> movieUserEntities;

    private boolean isExisting;

    @Builder.Default
    private Long qualifiedPerson = 0L;

}
