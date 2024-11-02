package com.unicamp.navable_api.services.impl;

import com.unicamp.navable_api.api.model.EstabelecimentoDTO;
import com.unicamp.navable_api.persistance.entities.Estabelecimento;
import com.unicamp.navable_api.persistance.repositories.EstabelecimentoRepository;
import com.unicamp.navable_api.services.mappers.EstabelecimentoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EstabelecimentoService {

    @Autowired
    private EstabelecimentoRepository estabelecimentoRepository;

    // Usar o mapper gerado pelo MapStruct
    private final EstabelecimentoMapper estabelecimentoMapper = EstabelecimentoMapper.INSTANCE;

    public EstabelecimentoDTO createEstabelecimento(EstabelecimentoDTO estabelecimentoDTO) {
        // Usar o mapper para converter DTO para Entidade
        Estabelecimento estabelecimento = estabelecimentoMapper.toEntity(estabelecimentoDTO);
        Estabelecimento savedEstabelecimento = estabelecimentoRepository.save(estabelecimento);
        // Retornar o DTO a partir da entidade salva
        return estabelecimentoMapper.toDTO(savedEstabelecimento);
    }

    public List<EstabelecimentoDTO> getAllEstabelecimentos() {
        List<Estabelecimento> estabelecimentos = estabelecimentoRepository.findAll();
        return estabelecimentos.stream()
                .map(estabelecimentoMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<EstabelecimentoDTO> getAllEstabelecimentosNearby(double latitude, double longitude) {
        List<Estabelecimento> estabelecimentos = estabelecimentoRepository.findNearby(latitude, longitude);
        return estabelecimentos.stream()
                .map(estabelecimentoMapper::toDTO)
                .collect(Collectors.toList());
    }

    public EstabelecimentoDTO getEstabelecimentoById(Integer id) {
        Estabelecimento estabelecimento = estabelecimentoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Estabelecimento not found with id " + id));
        return estabelecimentoMapper.toDTO(estabelecimento);
    }

    public List<EstabelecimentoDTO> filtrar(Float nota, List<Integer> categorias, Integer tipoId) {
        List<Estabelecimento> estabelecimentos = estabelecimentoRepository.findByNotaAndCategoriaAndTipo(nota, categorias, tipoId);
        return estabelecimentos.stream()
                .map(estabelecimentoMapper::toDTO)
                .collect(Collectors.toList());
    }
}
