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

    private static final EstabelecimentoMapper estabelecimentoMapper = EstabelecimentoMapper.INSTANCE;

    public EstabelecimentoDTO createEstabelecimento(EstabelecimentoDTO estabelecimentoDTO) {
        Estabelecimento estabelecimento = estabelecimentoMapper.toEntity(estabelecimentoDTO);
        Estabelecimento savedEstabelecimento = estabelecimentoRepository.save(estabelecimento);

        return estabelecimentoMapper.toDTO(savedEstabelecimento);
    }

    public List<EstabelecimentoDTO> getAllEstabelecimentosNearby(double latitude, double longitude) {
        List<Estabelecimento> nearbyResults = estabelecimentoRepository.findNearby(latitude, longitude);

        return nearbyResults.stream()
                .map(estabelecimentoMapper::toDTO)
                .toList();
    }

    public EstabelecimentoDTO getEstabelecimentoById(Integer id) {
        Estabelecimento estabelecimento = estabelecimentoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Estabelecimento not found with id " + id));

        Double nota = getAverageNotaByEstabelecimentoId(id);

        EstabelecimentoDTO estabelecimentoDTO = estabelecimentoMapper.toDTO(estabelecimento);
        estabelecimentoDTO.setNota(nota);

        return estabelecimentoDTO;
    }

    private Double getAverageNotaByEstabelecimentoId(Integer id) {
        return estabelecimentoRepository.findAverageNotaByEstabelecimentoId(id);
    }

    public List<EstabelecimentoDTO> filtrar(Float nota, List<Integer> categorias, Integer tipoId) {
        List<Estabelecimento> estabelecimentos = estabelecimentoRepository.findByNotaAndCategoriaAndTipo(nota, categorias, tipoId);
        return estabelecimentos.stream()
                .map(estabelecimentoMapper::toDTO)
                .toList();
    }
}
