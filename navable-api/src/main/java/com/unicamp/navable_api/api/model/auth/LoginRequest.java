package com.unicamp.navable_api.api.model.auth;

import lombok.*;

@Getter
@Setter
public class LoginRequest {
    private String email;
    private String password;
}
