package com.aplicacion.streaming.domain.model;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    private static final Long AGE_MAX = 11l;

    private Long id;
    private String	correo;
    private Integer	edad;
    private String password;
    private Set<MovieUser> movieUserEntities;


    public void validateAge(){
        if(AGE_MAX>this.edad){
            throw new IllegalArgumentException("You do not meet the age stipulated by the system");
        }
    }
}
