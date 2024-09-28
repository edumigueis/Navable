package com.unicamp.navable_api.services.impl;

import com.unicamp.navable_api.api.model.OcorrenciaDTO;
import com.unicamp.navable_api.persistance.entities.Ocorrencia;
import com.unicamp.navable_api.persistance.repositories.OcorrenciaRepository;
import com.unicamp.navable_api.services.mappers.OcorrenciaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OcorrenciaService {

    @Autowired
    private OcorrenciaRepository ocorrenciaRepository;

    // Usar o mapper gerado pelo MapStruct
    private final OcorrenciaMapper ocorrenciaMapper = OcorrenciaMapper.INSTANCE;

    public OcorrenciaDTO createOcorrencia(OcorrenciaDTO ocorrenciaDTO) {
        Ocorrencia ocorrencia = ocorrenciaMapper.toEntity(ocorrenciaDTO);
        Ocorrencia savedOcorrencia = ocorrenciaRepository.save(ocorrencia);
        return ocorrenciaMapper.toDTO(savedOcorrencia);
    }

    public List<OcorrenciaDTO> getAllOcorrencias() {
        List<Ocorrencia> ocorrencias = ocorrenciaRepository.findAll();
        return ocorrencias.stream()
                .map(ocorrenciaMapper::toDTO)
                .collect(Collectors.toList());
    }

    public OcorrenciaDTO getOcorrenciaById(Integer id) {
        Ocorrencia ocorrencia = ocorrenciaRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Ocorrencia not found with id " + id));
        return ocorrenciaMapper.toDTO(ocorrencia);
    }

}
