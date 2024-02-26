package com.aplicacion.streaming.infra.movie.entity;

import com.aplicacion.streaming.domain.valueObject.MovieType;
import com.aplicacion.streaming.infra.genre.entity.GenreEntity;
import com.aplicacion.streaming.infra.movieUser.entity.MovieUserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.Id;

import java.util.Set;

@Entity
@Table(name = "tcap_movies")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MovieEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    private String objectId;

    @ManyToOne
    @JoinColumn(name = "genre_id")
    private GenreEntity genre;

    @Enumerated(EnumType.STRING)
    private MovieType type;

    @Builder.Default
    private Long noViewers = 0L;
    @Builder.Default
    private Long score = 0L;

    @OneToMany(mappedBy = "movieEntity", fetch = FetchType.EAGER)
    private Set<MovieUserEntity> movieUserEntities;

    @Builder.Default
    private Long qualifiedPerson = 0L;


}
