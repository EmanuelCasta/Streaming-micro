package com.aplicacion.streaming.infra.user.adapter.repository;

import com.aplicacion.streaming.infra.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUserMongoRepository extends JpaRepository<UserEntity,Long> {

    Optional<UserEntity> findByCorreo(String correo);
}
