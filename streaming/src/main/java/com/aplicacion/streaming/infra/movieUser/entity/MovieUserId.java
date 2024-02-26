package com.aplicacion.streaming.infra.movieUser.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MovieUserId implements Serializable {
    private Long movieId;
    private Long userId;
}
