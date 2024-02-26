package com.aplicacion.streaming.infra.movieUser.entity;

import com.aplicacion.streaming.infra.movie.entity.MovieEntity;
import com.aplicacion.streaming.infra.user.entity.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tcap_movies_users")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MovieUserEntity {
    @EmbeddedId
    private MovieUserId id;


    @MapsId("movieId")
    @ManyToOne
    @JoinColumn(name = "movie_id")
    private MovieEntity movieEntity;

    @MapsId("userId")
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    private Boolean esGusto;

    private Long score;

    private Long noViewViews;

    private Boolean esVista;

}
