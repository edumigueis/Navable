package com.unicamp.navable_api.services.impl;

import com.unicamp.navable_api.api.model.auth.LoginResponse;
import com.unicamp.navable_api.persistance.entities.Usuario;
import com.unicamp.navable_api.persistance.repositories.UsuarioRepository;
import com.unicamp.navable_api.services.exceptions.CredencialesInvalidasException;
import com.unicamp.navable_api.services.exceptions.UsuarioNoEncontradoException;
import com.unicamp.navable_api.services.mappers.UsuarioMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    //@Autowired
    //private JwtEncoder jwtEncoder;

    private final UsuarioMapper usuarioMapper = UsuarioMapper.INSTANCE;
    
    public LoginResponse authenticate(String email, String password) {
        Usuario usuario = usuarioRepository.findByEmail(email)
            .orElseThrow(() -> new UsuarioNoEncontradoException(email));
            
        if (!password.matches(usuario.getSenha())) {
            throw new CredencialesInvalidasException();
        }
        
        String token = generateToken(usuario);
        return new LoginResponse(usuarioMapper.toDTO(usuario), token);
    }
    
    private String generateToken(Usuario usuario) {
        return "";
        // Generate JWT with appropriate claims and expiration
    }
}