package com.aplicacion.streaming.infra.movie.adapter.repository;

import com.aplicacion.streaming.infra.movie.entity.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import static com.aplicacion.streaming.infra.movie.adapter.repository.queryScript.MovieUserScript.*;

public interface IMoviePostgressqlRepository extends JpaRepository<MovieEntity,Long> {
    @Modifying
    @Query(QUERY_SUM_NUMBER_VIEWERS)
    void sumViewMovie(@Param("id") Long id);


    @Modifying
    @Query(QUERY_CHANGE_SCORE)
    void changeScore(@Param("id") Long id,@Param("oldScore") Long oldScore,@Param("newScore") Long newScore);

    @Modifying
    @Query(QUERY_SUM_PEOPLE_QUALI)
    void sumQualifer(@Param("id") Long id);

    @Modifying
    @Query(QUERY_LESS_PEOPLE_QUALI)
    void lessQualifer(@Param("id") Long id);


}
