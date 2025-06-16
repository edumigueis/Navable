package com.unicamp.navable_api.services.impl;

import com.unicamp.navable_api.api.model.auth.LoginResponse;
import com.unicamp.navable_api.persistance.entities.Usuario;
import com.unicamp.navable_api.persistance.repositories.UsuarioRepository;
import com.unicamp.navable_api.services.exceptions.CredencialesInvalidasException;
import com.unicamp.navable_api.services.exceptions.UsuarioNoEncontradoException;
import com.unicamp.navable_api.services.mappers.UsuarioMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class AuthService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtEncoder jwtEncoder;

    private final UsuarioMapper usuarioMapper = UsuarioMapper.INSTANCE;

    public LoginResponse authenticate(String email, String password) {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new UsuarioNoEncontradoException(email));

        if (!passwordEncoder.matches(password, usuario.getSenha())) {
            throw new CredencialesInvalidasException();
        }

        String token = generateToken(usuario);
        return new LoginResponse(usuarioMapper.toDTO(usuario), token);
    }

    private String generateToken(Usuario usuario) {
        Instant now = Instant.now();
        long expirySeconds = 86400000;

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("navable-api")
                .subject(usuario.getEmail())
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expirySeconds))
                .claim("id", usuario.getIdUsuario())
                .build();

        return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }
}
