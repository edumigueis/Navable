package com.unicamp.navable_api.api.impl;

import com.unicamp.navable_api.api.model.*;
import com.unicamp.navable_api.services.impl.*;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/{userId}")
    public UsuarioDTO getMyProfile(@PathVariable Integer userId) {
        return usuarioService.getUsuarioById(userId);
    }

    @GetMapping("/selos/{userId}")
    public List<SeloDTO> getMySelos(@PathVariable Integer userId) {
        return usuarioService.getSelosByUserId(userId);
    }

    @GetMapping("/categorias/{userId}")
    public List<CategoriaAcessibilidadeDTO> getMyCategorias(@PathVariable Integer userId) {
        return usuarioService.getCategoriasByUserId(userId);
    }

    @PostMapping("/selo/{userId}/{seloId}")
    public void addSelo(@PathVariable Integer userId, @PathVariable Integer seloId) {
        usuarioService.addSeloToUsuario(userId, seloId);
    }

    @PostMapping("/vote/{userId}/{ocorrenciaId}")
    public void vote(@PathVariable Integer userId, @PathVariable Integer ocorrenciaId) {
        usuarioService.voteOnOcorrencia(userId, ocorrenciaId);
    }

    @PostMapping("/categoria/{userId}")
    public void addCategoria(@PathVariable Integer userId, @RequestBody List<Integer> categoriaIds) {
        usuarioService.addCategoriaToUsuario(userId, categoriaIds);
    }

    @PatchMapping("/categorias/{userId}")
    public void updateCategorias(@PathVariable Integer userId, @RequestBody List<Integer> categoriaIds) {
        usuarioService.updateCategoriasToUsuario(userId, categoriaIds);
    }

    @GetMapping
    public List<UsuarioDTO> getAllUsuarios() {
        return usuarioService.getAllUsuarios();
    }

    @DeleteMapping("/{id}")
    public void deleteUsuario(@PathVariable Integer id) {
        usuarioService.deleteUsuario(id);
    }
}
