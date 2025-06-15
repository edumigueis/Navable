package com.unicamp.navable_api.services.impl;

import com.unicamp.navable_api.api.model.AvaliacaoDTO;
import com.unicamp.navable_api.persistance.entities.Avaliacao;
import com.unicamp.navable_api.persistance.repositories.AvaliacaoRepository;
import com.unicamp.navable_api.services.filters.*;
import com.unicamp.navable_api.services.mappers.AvaliacaoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class AvaliacaoService {

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    private final AvaliacaoMapper avaliacaoMapper = AvaliacaoMapper.INSTANCE;

    public AvaliacaoDTO createAvaliacao(AvaliacaoDTO avaliacaoDTO) {
        if (avaliacaoDTO.getNota() == null || avaliacaoDTO.getNota() < 1 || avaliacaoDTO.getNota() > 5) {
            throw new IllegalArgumentException("Nota must be between 1 and 5");
        }

        if (avaliacaoDTO.getIdEstabelecimento() == null || avaliacaoDTO.getIdEstabelecimento() <= 0) {
            throw new IllegalArgumentException("Estabelecimento ID is invalid");
        }

        Avaliacao avaliacao = avaliacaoMapper.toEntity(avaliacaoDTO);
        Avaliacao savedAvaliacao = avaliacaoRepository.save(avaliacao);
        return avaliacaoMapper.toDTO(savedAvaliacao);
    }

    public List<AvaliacaoDTO> getAvaliacoesByEstabelecimentoAndFilters(Integer estabelecimentoId, Integer nota, LocalDate dataInicial, LocalDate dataFinal) {
        if (estabelecimentoId == null || estabelecimentoId <= 0) {
            throw new IllegalArgumentException("Estabelecimento ID is invalid");
        }
        List<Avaliacao> avaliacoes = avaliacaoRepository.findByEstabelecimentoId(estabelecimentoId);
        if (avaliacoes.isEmpty()) {
            throw new IllegalArgumentException("No avaliacoes found for estabelecimento with id " + estabelecimentoId);
        }
        List<FiltroAvaliacaoStrategy> filtros = List.of(
                new FiltroPorNota(nota),
                new FiltroPorDataInicial(dataInicial),
                new FiltroPorDataFinal(dataFinal)
        );
        List<Avaliacao> filtradas = avaliacoes.stream()
                .filter(a -> filtros.stream().allMatch(f -> f.aplicar(a)))
                .toList();
        return filtradas.stream().map(avaliacaoMapper::toDTO)
                .toList();
    }

    public List<AvaliacaoDTO> getAvaliacoesByUsuarioAndFilters(Integer usuarioId, Integer nota, LocalDate dataInicial, LocalDate dataFinal) {
        if (usuarioId == null || usuarioId <= 0) {
            throw new IllegalArgumentException("Usuario ID is invalid");
        }
        List<Avaliacao> avaliacoes = avaliacaoRepository.findByUsuarioId(usuarioId);
        if (avaliacoes.isEmpty()) {
            throw new IllegalArgumentException("No avaliacoes found for usuario with id " + usuarioId);
        }
        List<FiltroAvaliacaoStrategy> filtros = List.of(
                new FiltroPorNota(nota),
                new FiltroPorDataInicial(dataInicial),
                new FiltroPorDataFinal(dataFinal)
        );

        List<Avaliacao> filtradas = avaliacoes.stream()
                .filter(a -> filtros.stream().allMatch(f -> f.aplicar(a)))
                .toList();

        return filtradas.stream().map(avaliacaoMapper::toDTO)
                .toList();
    }

    public AvaliacaoDTO getAvaliacaoById(Integer avaliacaoId) {
        if (avaliacaoId <= 0) {
            throw new IllegalArgumentException("Invalid Avaliacao ID");
        }

        Avaliacao avaliacao = avaliacaoRepository.findById(avaliacaoId)
                .orElseThrow(() -> new IllegalArgumentException("Avaliacao not found with id " + avaliacaoId));
        return avaliacaoMapper.toDTO(avaliacao);
    }
}
