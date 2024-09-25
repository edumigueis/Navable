package com.unicamp.navable_api.api.impl;

import com.unicamp.navable_api.api.model.EstabelecimentoDTO;
import com.unicamp.navable_api.services.impl.EstabelecimentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/{id}")
    public ResponseEntity<EstabelecimentoDTO> getEstabelecimentoById(@PathVariable Integer id) {
        return ResponseEntity.ok(estabelecimentoService.getEstabelecimentoById(id));
    }

    @GetMapping("/tipo/{tipoId}")
    public ResponseEntity<List<EstabelecimentoDTO>> getEstabelecimentosByTipo(@PathVariable Integer tipoId) {
        return ResponseEntity.ok(estabelecimentoService.getEstabelecimentosByTipo(tipoId));
    }
}
