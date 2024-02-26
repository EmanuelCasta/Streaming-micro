package com.aplicacion.streaming.application.dto.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTOCommand {
    private String	correo;
    private Integer	edad;
    private String password;
}
