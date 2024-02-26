package com.aplicacion.streaming.infra.user.adapter.repository;

import com.aplicacion.streaming.domain.model.User;
import com.aplicacion.streaming.domain.port.in.repository.IUserRepository;
import com.aplicacion.streaming.infra.user.builder.UserBuilder;
import com.aplicacion.streaming.infra.user.entity.UserEntity;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserMongoRepository implements IUserRepository {
    private IUserMongoRepository iUserMongoRepository;

    public UserMongoRepository(IUserMongoRepository iUserMongoRepository) {
        this.iUserMongoRepository = iUserMongoRepository;
    }



    @Override
    public void createUser(User user) {
        this.iUserMongoRepository.save(UserBuilder.convertEntity(user));
    }

    @Override
    public User getUserByEmail(String email) {
        Optional<UserEntity> userEntity = this.iUserMongoRepository.findByCorreo(email);
        return userEntity.map(UserBuilder::convertModel).orElse(null);
    }
}
