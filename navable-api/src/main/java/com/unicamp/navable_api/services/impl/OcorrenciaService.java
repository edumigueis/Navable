package com.unicamp.navable_api.services.impl;

import com.unicamp.navable_api.api.model.*;
import com.unicamp.navable_api.persistance.entities.*;
import com.unicamp.navable_api.persistance.repositories.*;
import com.unicamp.navable_api.services.mappers.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OcorrenciaService {

    @Autowired
    private OcorrenciaRepository ocorrenciaRepository;
    @Autowired
    private TipoOcorrenciaRepository tipoOcorrenciaRepository;

    private final OcorrenciaMapper ocorrenciaMapper = OcorrenciaMapper.INSTANCE;
    private final TipoOcorrenciaMapper tipoOcorrenciaMapper = TipoOcorrenciaMapper.INSTANCE;

    public OcorrenciaDTO createOcorrencia(OcorrenciaDTO ocorrenciaDTO) {
        Ocorrencia ocorrencia = ocorrenciaMapper.toEntity(ocorrenciaDTO);
        Ocorrencia savedOcorrencia = ocorrenciaRepository.save(ocorrencia);
        return ocorrenciaMapper.toDTO(savedOcorrencia);
    }

    public List<TipoOcorrenciaDTO> getAllTypes() {
        List<TipoOcorrencia> types = tipoOcorrenciaRepository.findAll();
        return types.stream()
                .map(tipoOcorrenciaMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<OcorrenciaDTO> getAllOcorrencias(double latitude, double longitude) {
        List<Ocorrencia> ocorrencias = ocorrenciaRepository.findNearby(latitude, longitude);

        return ocorrencias.stream()
                .map(ocorrencia -> {
                    OcorrenciaDTO dto = ocorrenciaMapper.toDTO(ocorrencia);

                    tipoOcorrenciaRepository.findById(ocorrencia.getIdTipoOcorrencia())
                            .ifPresent((tipo) -> dto.setTipoOcorrencia(tipoOcorrenciaMapper.toDTO(tipo)));

                    return dto;
                })
                .collect(Collectors.toList());
    }

    public OcorrenciaDTO getOcorrenciaById(Integer id) {
        Ocorrencia ocorrencia = ocorrenciaRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Ocorrencia not found with id " + id));
        return ocorrenciaMapper.toDTO(ocorrencia);
    }
}
