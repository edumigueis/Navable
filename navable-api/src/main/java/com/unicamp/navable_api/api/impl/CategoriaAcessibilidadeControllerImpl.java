package com.unicamp.navable_api.api.impl;

import com.unicamp.navable_api.api.model.CategoriaAcessibilidadeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categorias-acessibilidade")
public class CategoriaAcessibilidadeControllerImpl {

    @Autowired
    private CategoriaAcessibilidadeDTO categoriaService;

    @GetMapping
    public List<CategoriaAcessibilidadeDTO> getAllCategorias() {
        return categoriaService.getAllCategorias();
    }

    @GetMapping("/{id}")
    public CategoriaAcessibilidadeDTO getCategoriaById(@PathVariable Integer id) {
        return categoriaService.getCategoriaById(id);
    }

    @PostMapping("/user/{usuarioId}")
    public void setCategoriaToUsuario(@PathVariable Integer usuarioId, @RequestBody List<Integer> categoriaIds) {
        usuarioService.setCategoriaToUsuario(usuarioId, categoriaIds);
    }
}
