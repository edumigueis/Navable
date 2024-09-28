package com.unicamp.navable_api.api.impl;

import com.unicamp.navable_api.api.model.OcorrenciaDTO;
import com.unicamp.navable_api.services.impl.OcorrenciaService;
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
        return ResponseEntity.ok(ocorrenciaService.getAllOcorrencias());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OcorrenciaDTO> getOcorrenciaById(@PathVariable Integer id) {
        return ResponseEntity.ok(ocorrenciaService.getOcorrenciaById(id));
    }

    @PostMapping
    public ResponseEntity<OcorrenciaDTO> createOcorrencia(@RequestBody OcorrenciaDTO ocorrenciaDTO) {
        return ResponseEntity.ok(ocorrenciaService.createOcorrencia(ocorrenciaDTO));
    }
}

