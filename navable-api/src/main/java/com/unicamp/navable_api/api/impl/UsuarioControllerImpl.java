package com.unicamp.navable_api.api.impl;

import com.unicamp.navable_api.api.model.*;
import com.unicamp.navable_api.services.auth.AuthService;
import com.unicamp.navable_api.services.exceptions.CredencialesInvalidasException;
import com.unicamp.navable_api.services.exceptions.UsuarioNoEncontradoException;
import com.unicamp.navable_api.services.impl.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.unicamp.navable_api.services.exceptions.CredencialesInvalidasException;
import com.unicamp.navable_api.services.exceptions.UsuarioNoEncontradoException;
import com.unicamp.navable_api.services.auth.AuthService;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioControllerImpl {

    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private AuthService authService;

    @GetMapping
    public List<UsuarioDTO> getAllUsuarios() {
        return usuarioService.getAllUsuarios();
    }

    @GetMapping("/selos/{id}")
    public List<SeloDTO> getAllSelosByUsuario(@PathVariable Integer id) {
        return usuarioService.getSelosByUserId(id);
    }

    @GetMapping("/categorias/{id}")
    public List<CategoriaAcessibilidadeDTO> getAllCategoriasByUsuario(@PathVariable Integer id) {
        return usuarioService.getCategoriasByUserId(id);
    }

    @PostMapping("/signin")
    public ResponseEntity<UsuarioDTO> signIn(@RequestParam String email, @RequestParam String password) {
    try {
        return ResponseEntity.ok(authService.authenticate(email, password));
    } catch (CredencialesInvalidasException | UsuarioNoEncontradoException ex) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }
}

    @GetMapping("/{id}")
    public UsuarioDTO getUsuarioById(@PathVariable Integer id) {
        return usuarioService.getUsuarioById(id);
    }

    @PostMapping
    public UsuarioDTO createUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        return usuarioService.createUsuario(usuarioDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteUsuario(@PathVariable Integer id) {
        usuarioService.deleteUsuario(id);
    }

    @PostMapping("/{usuarioId}/selo/{seloId}")
    public void addSeloToUsuario(@PathVariable Integer usuarioId, @PathVariable Integer seloId) {
        usuarioService.addSeloToUsuario(usuarioId, seloId);
    }

    @PostMapping("/{usuarioId}/vote/{ocorrenciaId}")
    public void voteOnOcorrencia(@PathVariable Integer usuarioId, @PathVariable Integer ocorrenciaId) {
        usuarioService.voteOnOcorrencia(usuarioId, ocorrenciaId);
    }

    @PostMapping("/{usuarioId}/categoria")
    public void addCategoriaToUsuario(@PathVariable Integer usuarioId, @RequestBody List<Integer> categoriaIds) {
        usuarioService.addCategoriaToUsuario(usuarioId, categoriaIds);
    }

    @PatchMapping("/{usuarioId}/categorias")
    public void updateCategoriasToUsuario(@PathVariable Integer usuarioId, @RequestBody List<Integer> categoriaIds) {
        usuarioService.updateCategoriasToUsuario(usuarioId, categoriaIds);
    }
}
