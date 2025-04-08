package com.unicamp.navable_api.api.impl;

import com.unicamp.navable_api.api.model.AvaliacaoDTO;
import com.unicamp.navable_api.services.impl.AvaliacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/avaliacoes")
public class AvaliacaoControllerImpl {

    @Autowired
    private AvaliacaoService avaliacaoService;

    @PostMapping
    public ResponseEntity<AvaliacaoDTO> createAvaliacao(@RequestBody AvaliacaoDTO avaliacaoDTO) {
        AvaliacaoDTO createdAvaliacao = avaliacaoService.createAvaliacao(avaliacaoDTO);
        return ResponseEntity.status(201).body(createdAvaliacao);
    }

    @GetMapping("/estabelecimento/{estabelecimentoId}")
    public ResponseEntity<List<AvaliacaoDTO>> getAvaliacoesByEstabelecimento(
            @PathVariable Integer estabelecimentoId,
            @RequestParam(required = false) Integer nota,
            @RequestParam(required = false) LocalDate dataInicial,
            @RequestParam(required = false) LocalDate dataFinal) {

        List<AvaliacaoDTO> avaliacoes = avaliacaoService.getAvaliacoesByEstabelecimentoAndFilters(
                estabelecimentoId, nota, dataInicial, dataFinal);
        return ResponseEntity.ok(avaliacoes);
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<AvaliacaoDTO>> getAvaliacoesByUsuario(
            @PathVariable Integer usuarioId,
            @RequestParam(required = false) Integer nota,
            @RequestParam(required = false) LocalDate dataInicial,
            @RequestParam(required = false) LocalDate dataFinal) {

        List<AvaliacaoDTO> avaliacoes = avaliacaoService.getAvaliacoesByUsuarioAndFilters(
                usuarioId, nota, dataInicial, dataFinal);
        return ResponseEntity.ok(avaliacoes);
    }

    @GetMapping("/{avaliacaoId}")
    public ResponseEntity<AvaliacaoDTO> getAvaliacaoById(@PathVariable Integer avaliacaoId) {
        AvaliacaoDTO avaliacao = avaliacaoService.getAvaliacaoById(avaliacaoId);
        return ResponseEntity.ok(avaliacao);
    }
}
