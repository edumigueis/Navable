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

    private final EstabelecimentoMapper estabelecimentoMapper = EstabelecimentoMapper.INSTANCE;

    public EstabelecimentoDTO createEstabelecimento(EstabelecimentoDTO estabelecimentoDTO) {
        Estabelecimento estabelecimento = estabelecimentoMapper.toEntity(estabelecimentoDTO);
        Estabelecimento savedEstabelecimento = estabelecimentoRepository.save(estabelecimento);

        return estabelecimentoMapper.toDTO(savedEstabelecimento);
    }

    public List<EstabelecimentoDTO> getAllEstabelecimentosNearby(double latitude, double longitude) {
        List<Object[]> results = estabelecimentoRepository.findNearby(latitude, longitude);

        return results.stream().map(row -> {
            EstabelecimentoDTO dto = new EstabelecimentoDTO();
            dto.setIdEstabelecimento((Integer) row[0]);
            dto.setIdTipoEstabeleci((Integer) row[1]);
            dto.setNome((String) row[2]);
            dto.setLatitude((Double) row[3]);
            dto.setLongitude((Double) row[4]);
            dto.setImagem((String) row[5]);
            dto.setEndereco((String) row[6]);
            dto.setNota(row[7] != null ? ((Number) row[7]).doubleValue() : 0.0);
            return dto;
        }).collect(Collectors.toList());
    }

    public EstabelecimentoDTO getEstabelecimentoById(Integer id) {
        Estabelecimento estabelecimento = estabelecimentoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Estabelecimento not found with id " + id));

        Double nota = estabelecimentoRepository.findAverageNotaByEstabelecimentoId(id);

        EstabelecimentoDTO estabelecimentoDTO = estabelecimentoMapper.toDTO(estabelecimento);
        estabelecimentoDTO.setNota(nota);

        return estabelecimentoDTO;
    }

    public List<EstabelecimentoDTO> filtrar(Float nota, List<Integer> categorias, Integer tipoId) {
        List<Estabelecimento> estabelecimentos = estabelecimentoRepository.findByNotaAndCategoriaAndTipo(nota, categorias, tipoId);
        return estabelecimentos.stream()
                .map(estabelecimentoMapper::toDTO)
                .collect(Collectors.toList());
    }
}
