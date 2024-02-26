package com.aplicacion.streaming.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MovieObjectIDTO {
    private String idObject;
    private Boolean isExiting;
}
