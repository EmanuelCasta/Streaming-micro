package com.aplicacion.streaming.infra.genre.entity;

import com.aplicacion.streaming.infra.movie.entity.MovieEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.Id;

import java.util.List;
@Entity
@Table(name = "tcap_genres")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GenreEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String idObject;

    @Column(unique = true)
    private String name;

    @OneToMany(mappedBy = "genre")
    private List<MovieEntity> movieEntities;
}
