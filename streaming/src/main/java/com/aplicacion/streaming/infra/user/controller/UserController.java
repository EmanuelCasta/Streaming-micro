package com.aplicacion.streaming.infra.user.controller;

import com.aplicacion.streaming.application.dto.LoginDTO;
import com.aplicacion.streaming.application.dto.UserResponseDTO;
import com.aplicacion.streaming.application.dto.command.UserDTOCommand;
import com.aplicacion.streaming.application.handler.UserHandlerCommand;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user/")
public class UserController {
    private final UserHandlerCommand userHandlerCommand;

    public UserController(UserHandlerCommand userHandlerCommand) {
        this.userHandlerCommand = userHandlerCommand;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void placeUser(@RequestBody UserDTOCommand userDTOCommand){
        this.userHandlerCommand.handle(userDTOCommand);
    }


    @PostMapping("access")
    @ResponseStatus(HttpStatus.CONTINUE)
    public ResponseEntity<UserResponseDTO> accessUser(@RequestBody LoginDTO loginDTO){
        UserResponseDTO response =  this.userHandlerCommand.handle(loginDTO);
        if (response!=null) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }


}
