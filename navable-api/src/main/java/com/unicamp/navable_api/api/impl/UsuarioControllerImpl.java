package com.unicamp.navable_api.api.impl;

import com.unicamp.navable_api.api.model.UsuarioDTO;
import com.unicamp.navable_api.services.impl.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioControllerImpl {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public List<UsuarioDTO> getAllUsuarios() {
        return usuarioService.getAllUsuarios();
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
}
