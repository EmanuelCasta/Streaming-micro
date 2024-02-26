package com.aplicacion.streaming.infra.movie.adapter.repository.queryScript;

public class MovieUserScript {

    private MovieUserScript(){
        throw new IllegalArgumentException("Utility class");
    }

    public final static String UPDATE_TABLE = "UPDATE MovieEntity m";
    public final static String QUERY_SUM_NUMBER_VIEWERS = UPDATE_TABLE+" SET m.noViewers = m.noViewers + 1 WHERE m.id = :id";

    public final static String QUERY_SUM_PEOPLE_QUALI= UPDATE_TABLE+" SET m.qualifiedPerson = m.qualifiedPerson + 1 WHERE m.id = :id";
    public final static String QUERY_LESS_PEOPLE_QUALI= UPDATE_TABLE+" SET m.qualifiedPerson = m.qualifiedPerson - 1 WHERE m.id = :id";

    public final static String QUERY_CHANGE_SCORE= UPDATE_TABLE+" SET m.score = m.score + :newScore - :oldScore WHERE m.id = :id";
}
