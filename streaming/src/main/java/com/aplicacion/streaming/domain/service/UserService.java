package com.aplicacion.streaming.domain.service;

import com.aplicacion.streaming.domain.model.User;
import com.aplicacion.streaming.domain.port.in.repository.IUserRepository;
import com.aplicacion.streaming.domain.port.in.services.IUserServices;

import java.util.Objects;

public class UserService implements IUserServices {

    private final IUserRepository iUserRepository;


    public UserService(IUserRepository iUserRepository) {
        this.iUserRepository = iUserRepository;
    }

    @Override
    public void createUser(User user) {
        user.validateAge();
        this.iUserRepository.createUser(user);
    }

    @Override
    public User verifyUser(String email) {

        return this.iUserRepository.getUserByEmail(email);


    }
}
