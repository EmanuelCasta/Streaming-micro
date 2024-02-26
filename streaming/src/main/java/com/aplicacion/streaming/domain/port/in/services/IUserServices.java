package com.aplicacion.streaming.domain.port.in.services;

import com.aplicacion.streaming.domain.model.User;

public interface IUserServices {

    void createUser(User user);

    User verifyUser(String email);
}
