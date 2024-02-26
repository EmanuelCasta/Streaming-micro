package com.aplicacion.streaming.infra.user.entity;


import com.aplicacion.streaming.infra.movieUser.entity.MovieUserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table(name = "tcap_users")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String	correo;

    private Integer	edad;
    private String password;

    @OneToMany(mappedBy = "userEntity")
    private Set<MovieUserEntity> movieUserEntities;
}
