package com.unicamp.navable_api.services.impl;

import com.unicamp.navable_api.api.model.OcorrenciaDTO;
import com.unicamp.navable_api.persistance.entities.Ocorrencia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OcorrenciaService {

    @Autowired
    private OcorrenciaRepository ocorrenciaRepository;

    @Autowired
    private ModelMapper modelMapper;

    public OcorrenciaDTO createOcorrencia(OcorrenciaDTO ocorrenciaDTO) {
        Ocorrencia ocorrencia = modelMapper.map(ocorrenciaDTO, Ocorrencia.class);
        Ocorrencia savedOcorrencia = ocorrenciaRepository.save(ocorrencia);
        return modelMapper.map(savedOcorrencia, OcorrenciaDTO.class);
    }

    public List<OcorrenciaDTO> getAllOcorrencias() {
        List<Ocorrencia> ocorrencias = ocorrenciaRepository.findAll();
        return ocorrencias.stream()
                .map(ocorrencia -> modelMapper.map(ocorrencia, OcorrenciaDTO.class))
                .collect(Collectors.toList());
    }

    public OcorrenciaDTO getOcorrenciaById(Integer id) {
        Ocorrencia ocorrencia = ocorrenciaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ocorrencia not found with id " + id));
        return modelMapper.map(ocorrencia, OcorrenciaDTO.class);
    }

    public void deleteOcorrencia(Integer id) {
        ocorrenciaRepository.deleteById(id);
    }

    public List<OcorrenciaDTO> getOcorrenciasByTipo(Integer tipoId) {
        // Implement logic to get occurrences by type
        return null; // Placeholder for actual implementation
    }
}