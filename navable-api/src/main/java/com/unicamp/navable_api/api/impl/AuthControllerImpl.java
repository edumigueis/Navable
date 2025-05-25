package com.unicamp.navable_api.api.controller;

import com.unicamp.navable_api.api.model.UsuarioDTO;
import com.unicamp.navable_api.api.model.auth.LoginRequest;
import com.unicamp.navable_api.api.model.auth.LoginResponse;
import com.unicamp.navable_api.services.auth.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthControllerImpl {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        UsuarioDTO usuario = authService.authenticate(request.getEmail(), request.getPassword());
        return new LoginResponse(usuario);
    }
}