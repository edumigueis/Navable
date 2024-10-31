package com.unicamp.navable_api.api.model;

import lombok.*;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SeloUsuarioDTO {
    private Integer idUsuario;
    private Integer idSelo;
    private Date timestamp;
}

