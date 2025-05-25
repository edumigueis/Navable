package com.unicamp.navable_api.api.model.auth;


import com.unicamp.navable_api.api.model.UsuarioDTO;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
public class LoginResponse {
    private UsuarioDTO usuario;
    private String token;
}
