package com.aplicacion.streaming.application.dto.command;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MovieUserDTOCommand {

    private Long movieid;

    private Long userid;

    @Builder.Default
    private Boolean esGusto=null;

    @Builder.Default
    private Long score = null;

    @Builder.Default
    private Long noViewViews = 0L;

    @Builder.Default
    private Boolean esVista = false;

    @Builder.Default
    private Boolean esClickVideo= false;

}
