package com.unicamp.navable_api.services.exceptions;

public class UsuarioNoEncontradoException extends RuntimeException {
    public UsuarioNoEncontradoException(String email) {
        super("Usuário não encontrado com e-mail: " + email);
    }
}