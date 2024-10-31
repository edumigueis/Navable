package com.unicamp.navable_api.api.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO {
    private Integer idUsuario;
    private String nome;
    private String senha;
    private String email;
    private Integer pontos;
}
