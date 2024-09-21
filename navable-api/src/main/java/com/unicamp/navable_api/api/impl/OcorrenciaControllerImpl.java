package com.unicamp.navable_api.api.impl;

import com.unicamp.navable_api.api.model.OcorrenciaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ocorrencias")
public class OcorrenciaControllerImpl {

    @Autowired
    private OcorrenciaService ocorrenciaService;

    @GetMapping("/{latitude}/{longitude}")
    public ResponseEntity<List<OcorrenciaDTO>> getAllOcorrencias(@PathVariable Integer latitude, @PathVariable Integer longitude) {
        return ResponseEntity.ok(ocorrenciaService.getAllEstabelecimentos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OcorrenciaDTO> getEstabelecimentoById(@PathVariable Integer id) {
        return ResponseEntity.ok(ocorrenciaService.getEstabelecimentoById(id));
    }

    @PostMapping
    public ResponseEntity<OcorrenciaDTO> createOcorrencia(@RequestBody OcorrenciaDTO ocorrenciaDTO) {
        return ResponseEntity.ok(usuarioService.updateUsuario(id, usuarioDTO));
    }

    @PostMapping("/voto/{ocorrenciaId}/{userId}")
    public void voteOnOcorrencia(@PathVariable Integer ocorrenciaId, @PathVariable Integer userId) {
        usuarioService.voteOnOcorrencia(ocorrenciaId, userId);
    }
}

