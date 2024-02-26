package com.aplicacion.streaming.application.dto.command;

import com.aplicacion.streaming.domain.valueObject.MovieType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MovieDTOCommand {

    private String name;

    private Long genre_id;

    private MovieType type;

    @Builder.Default
    private Long score = 0L;


}
