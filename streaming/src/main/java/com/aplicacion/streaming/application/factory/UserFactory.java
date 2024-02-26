package com.aplicacion.streaming.application.factory;

import com.aplicacion.streaming.application.dto.command.UserDTOCommand;
import com.aplicacion.streaming.domain.model.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UserFactory {

    private UserFactory(){
        throw new IllegalArgumentException("Utility class");
    }

    public static User convertObject(UserDTOCommand userDTOCommand,BCryptPasswordEncoder passwordEncoder){
        String encodedPassword = passwordEncoder.encode(userDTOCommand.getPassword());

        return User.builder()
                .edad(userDTOCommand.getEdad())
                .correo(userDTOCommand.getCorreo().toLowerCase())
                .password(encodedPassword)
                .build();
    }
}
