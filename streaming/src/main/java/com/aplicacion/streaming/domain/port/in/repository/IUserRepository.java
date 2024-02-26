package com.aplicacion.streaming.domain.port.in.repository;

import com.aplicacion.streaming.domain.model.User;

public interface IUserRepository {

    void createUser(User user);

    User getUserByEmail(String email);
}
