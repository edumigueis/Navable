package com.unicamp.navable_api.api.model.auth;


import com.unicamp.navable_api.api.model.UsuarioDTO;

public class LoginResponse {
    private UsuarioDTO usuario;

    public LoginResponse(UsuarioDTO usuario) {
        this.usuario = usuario;
    }

    public UsuarioDTO getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioDTO usuario) {
        this.usuario = usuario;
    }
}
