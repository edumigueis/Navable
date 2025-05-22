package com.unicamp.navable_api.services.impl;

import com.unicamp.navable_api.api.model.AvaliacaoDTO;
import com.unicamp.navable_api.persistance.entities.Avaliacao;
import com.unicamp.navable_api.persistance.repositories.AvaliacaoRepository;
import com.unicamp.navable_api.services.mappers.AvaliacaoMapper;
import com.unicamp.navable_api.services.filters.FiltroAvaliacaoStrategy;
import com.unicamp.navable_api.services.filters.FiltroPorNota;
import com.unicamp.navable_api.services.filters.FiltroPorDataInicial;
import com.unicamp.navable_api.services.filters.FiltroPorDataFinal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class AvaliacaoService {

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    // Usar o mapper gerado pelo MapStruct
    private final AvaliacaoMapper avaliacaoMapper = AvaliacaoMapper.INSTANCE;

    public AvaliacaoDTO createAvaliacao(AvaliacaoDTO avaliacaoDTO) {
        Avaliacao avaliacao = avaliacaoMapper.toEntity(avaliacaoDTO);
        Avaliacao savedAvaliacao = avaliacaoRepository.save(avaliacao);
        return avaliacaoMapper.toDTO(savedAvaliacao);
    }

    public List<AvaliacaoDTO> getAvaliacoesByEstabelecimentoAndFilters(Integer estabelecimentoId, Integer nota, LocalDate dataInicial, LocalDate dataFinal) {
        List<Avaliacao> avaliacoes = avaliacaoRepository.findByEstabelecimentoId(estabelecimentoId);
        List<FiltroAvaliacaoStrategy> filtros = List.of(
                new FiltroPorNota(nota),
                new FiltroPorDataInicial(dataInicial),
                new FiltroPorDataFinal(dataFinal)
        );
        List<Avaliacao> filtradas = avaliacoes.stream()
                .filter(a -> filtros.stream().allMatch(f -> f.aplicar(a)))
                .toList();
        return filtradas.stream()
                .map(avaliacaoMapper::toDTO)
                .toList();
    }

    public List<AvaliacaoDTO> getAvaliacoesByUsuarioAndFilters(Integer usuarioId, Integer nota, LocalDate dataInicial, LocalDate dataFinal) {
        List<Avaliacao> avaliacoes = avaliacaoRepository.findByUsuarioId(usuarioId);
        List<FiltroAvaliacaoStrategy> filtros = List.of(
                new FiltroPorNota(nota),
                new FiltroPorDataInicial(dataInicial),
                new FiltroPorDataFinal(dataFinal)
        );

        List<Avaliacao> filtradas = avaliacoes.stream()
                .filter(a -> filtros.stream().allMatch(f -> f.aplicar(a)))
                .toList();

        return filtradas.stream()
                .map(avaliacaoMapper::toDTO)
                .toList();
    }

    public AvaliacaoDTO getAvaliacaoById(Integer avaliacaoId) {
        Avaliacao avaliacao = avaliacaoRepository.findById(avaliacaoId)
                .orElseThrow(() -> new IllegalArgumentException("Avaliacao not found with id " + avaliacaoId));
        return avaliacaoMapper.toDTO(avaliacao);
    }
}
