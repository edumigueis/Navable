package com.unicamp.navable_api.api.impl;

import com.unicamp.navable_api.api.model.auth.LoginRequest;
import com.unicamp.navable_api.api.model.auth.LoginResponse;
import com.unicamp.navable_api.services.impl.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthControllerImpl {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        return authService.authenticate(request.getEmail(), request.getPassword());
    }
}