package com.aplicacion.streaming.domain.model;


import com.aplicacion.streaming.infra.movieUser.entity.MovieUserId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MovieUser {

    private final static Long MAX_SCORE = 5L;
    private final static Long MIN_SCORE = 1L;

    private MovieUserId id;

    private Movie movie;

    private User user;

    private Boolean esGusto;

    private Long score;

    private Long noViewViews;

    private Boolean esVista;


    public void validateScore(){
        if(score!= null && (score<MIN_SCORE || score>MAX_SCORE)){
            throw new IllegalArgumentException("Range score is 1 and 5");
        }
    }
}
