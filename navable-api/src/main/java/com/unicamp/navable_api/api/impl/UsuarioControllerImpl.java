package com.unicamp.navable_api.api.impl;

import com.unicamp.navable_api.api.model.*;
import com.unicamp.navable_api.services.impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioControllerImpl {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private AuthService authService;

    @PostMapping
    public UsuarioDTO createUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        return usuarioService.createUsuario(usuarioDTO);
    }

    // Authenticated user actions
    @GetMapping("/profile")
    public UsuarioDTO getMyProfile(@AuthenticationPrincipal Jwt jwt) {
        String email = jwt.getSubject();
        return usuarioService.getUsuarioByEmail(email);
    }

    @GetMapping("/selos")
    public List<SeloDTO> getMySelos(@AuthenticationPrincipal Jwt jwt) {
        int userId = getUserIdFromToken(jwt);
        return usuarioService.getSelosByUserId(userId);
    }

    @GetMapping("/categorias")
    public List<CategoriaAcessibilidadeDTO> getMyCategorias(@AuthenticationPrincipal Jwt jwt) {
        int userId = getUserIdFromToken(jwt);
        return usuarioService.getCategoriasByUserId(userId);
    }

    @PostMapping("/selo/{seloId}")
    public void addSelo(@AuthenticationPrincipal Jwt jwt, @PathVariable Integer seloId) {
        int userId = getUserIdFromToken(jwt);
        usuarioService.addSeloToUsuario(userId, seloId);
    }

    @PostMapping("/vote/{ocorrenciaId}")
    public void vote(@AuthenticationPrincipal Jwt jwt, @PathVariable Integer ocorrenciaId) {
        int userId = getUserIdFromToken(jwt);
        usuarioService.voteOnOcorrencia(userId, ocorrenciaId);
    }

    @PostMapping("/categoria")
    public void addCategoria(@AuthenticationPrincipal Jwt jwt, @RequestBody List<Integer> categoriaIds) {
        int userId = getUserIdFromToken(jwt);
        usuarioService.addCategoriaToUsuario(userId, categoriaIds);
    }

    @PatchMapping("/categorias")
    public void updateCategorias(@AuthenticationPrincipal Jwt jwt, @RequestBody List<Integer> categoriaIds) {
        int userId = getUserIdFromToken(jwt);
        usuarioService.updateCategoriasToUsuario(userId, categoriaIds);
    }

    // Optional: Admin-only endpoints
    @GetMapping
    public List<UsuarioDTO> getAllUsuarios() {
        return usuarioService.getAllUsuarios();
    }

    @GetMapping("/{id}")
    public UsuarioDTO getUsuarioById(@PathVariable Integer id) {
        return usuarioService.getUsuarioById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteUsuario(@PathVariable Integer id) {
        usuarioService.deleteUsuario(id);
    }

    // Helper method
    private int getUserIdFromToken(Jwt jwt) {
        return jwt.getClaim("id");
    }
}
