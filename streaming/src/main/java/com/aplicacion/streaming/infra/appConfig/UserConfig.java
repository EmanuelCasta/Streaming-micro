package com.aplicacion.streaming.infra.appConfig;

import com.aplicacion.streaming.application.handler.UserHandlerCommand;
import com.aplicacion.streaming.domain.port.in.repository.IUserRepository;
import com.aplicacion.streaming.domain.port.in.services.IUserServices;
import com.aplicacion.streaming.domain.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfig {
    @Bean
    IUserServices iUserServices(IUserRepository iUserRepository){
        return new UserService(iUserRepository);

    }

    @Bean
    UserHandlerCommand userHandlerCommand(IUserServices iUserServices){
        return new UserHandlerCommand(iUserServices);
    }

}
