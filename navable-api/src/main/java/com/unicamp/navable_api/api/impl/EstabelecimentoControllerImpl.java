package com.unicamp.navable_api.api.impl;

import com.unicamp.navable_api.api.model.EstabelecimentoDTO;
import com.unicamp.navable_api.services.impl.EstabelecimentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estabelecimentos")
public class EstabelecimentoControllerImpl {

    @Autowired
    private EstabelecimentoService estabelecimentoService;

    @GetMapping
    public ResponseEntity<List<EstabelecimentoDTO>> getAllEstabelecimentos() {
        return ResponseEntity.ok(estabelecimentoService.getAllEstabelecimentos());
    }

    @GetMapping("/{latitude}/{longitude}")
    public ResponseEntity<List<EstabelecimentoDTO>> getAllEstabelecimentosNearby(@PathVariable double latitude, @PathVariable double longitude) {
        return ResponseEntity.ok(estabelecimentoService.getAllEstabelecimentosNearby(latitude, longitude));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstabelecimentoDTO> getEstabelecimentoById(@PathVariable Integer id) {
        return ResponseEntity.ok(estabelecimentoService.getEstabelecimentoById(id));
    }

    @GetMapping("/filtrar")
    public ResponseEntity<List<EstabelecimentoDTO>> getEstabelecimentosByTipo(@RequestParam Float nota, @RequestParam List<Integer> categorias, @RequestParam Integer tipoId) {
        return ResponseEntity.ok(estabelecimentoService.filtrar(nota, categorias, tipoId));
    }
}
