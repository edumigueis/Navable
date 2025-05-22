package com.unicamp.navable_api.services.exceptions;

public class CredencialesInvalidasException extends RuntimeException {
    public CredencialesInvalidasException() {
        super("Credenciais inválidas");
    }
}


