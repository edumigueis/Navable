package com.unicamp.navable_api.services.impl;

import com.unicamp.navable_api.api.model.AvaliacaoDTO;
import com.unicamp.navable_api.persistance.entities.Avaliacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AvaliacaoService {

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public AvaliacaoDTO createAvaliacao(AvaliacaoDTO avaliacaoDTO) {
        Avaliacao avaliacao = modelMapper.map(avaliacaoDTO, Avaliacao.class);
        Avaliacao savedAvaliacao = avaliacaoRepository.save(avaliacao);
        return modelMapper.map(savedAvaliacao, AvaliacaoDTO.class);
    }

    @Override
    public List<AvaliacaoDTO> getAllAvaliacoesByEstabelecimento(Integer estabelecimentoId) {
        List<Avaliacao> avaliacoes = avaliacaoRepository.findByIdEstabelecimento(estabelecimentoId);
        return avaliacoes.stream()
                .map(avaliacao -> modelMapper.map(avaliacao, AvaliacaoDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<AvaliacaoDTO> getAvaliacoesByUsuario(Integer usuarioId) {
        List<Avaliacao> avaliacoes = avaliacaoRepository.findByIdUsuario(usuarioId);
        return avaliacoes.stream()
                .map(avaliacao -> modelMapper.map(avaliacao, AvaliacaoDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public AvaliacaoDTO getAvaliacaoById(Integer avaliacaoId) {
        Avaliacao avaliacao = avaliacaoRepository.findById(avaliacaoId)
                .orElseThrow(() -> new ResourceNotFoundException("Avaliacao not found with id " + avaliacaoId));
        return modelMapper.map(avaliacao, AvaliacaoDTO.class);
    }
}