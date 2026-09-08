package com.unicamp.navable_api.services.impl;

import com.unicamp.navable_api.api.model.*;
import com.unicamp.navable_api.persistance.entities.*;
import com.unicamp.navable_api.persistance.repositories.*;
import com.unicamp.navable_api.services.mappers.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class OcorrenciaService {

    @Autowired
    private OcorrenciaRepository ocorrenciaRepository;
    @Autowired
    private TipoOcorrenciaRepository tipoOcorrenciaRepository;

    private static final OcorrenciaMapper ocorrenciaMapper = OcorrenciaMapper.INSTANCE;
    private static final TipoOcorrenciaMapper tipoOcorrenciaMapper = TipoOcorrenciaMapper.INSTANCE;
    private static final double ALERT_BUFFER_KM = 0.1; // 100 meters

    public OcorrenciaDTO createOcorrencia(OcorrenciaDTO ocorrenciaDTO) {
        Ocorrencia ocorrencia = ocorrenciaMapper.toEntity(ocorrenciaDTO);
        Ocorrencia savedOcorrencia = ocorrenciaRepository.save(ocorrencia);
        return ocorrenciaMapper.toDTO(saved0correncia);
    }

    public List<TipoOcorrenciaDTO> getAllTypes() {
        List<TipoOcorrencia> types = tipoOcorrenciaRepository.findAll();
        return types.stream()
                .map(tipoOcorrenciaMapper::toDTO)
                .toList();
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
                .toList();
    }

    public List<OcorrenciaDTO> getAlertsAlongPath(List<CoordinateDTO> path) {
        Set<Ocorrencia> nearbyIncidents = new HashSet<>();

        for (CoordinateDTO coord : path) {
            List<Ocorrencia> incidents = ocorrenciaRepository.findNearby(coord.getLatitude(), coord.getLongitude(), ALERT_BUFFER_KM);
            nearbyIncidents.addAll(incidents);
        }

        return nearbyIncidents.stream()
                .map(ocorrencia -> {
                    OcorrenciaDTO dto = ocorrenciaMapper.toDTO(ocorrencia);
                    tipoOcorrenciaRepository.findById(ocorrencia.getIdTipoOcorrencia())
                            .ifPresent((tipo) -> dto.setTipoOcorrencia(tipoOcorrenciaMapper.toDTO(tipo)));
                    return dto;
                })
                .toList();
    }

    public OcorrenciaDTO getOcorrenciaById(Integer id) {
        Ocorrencia ocorrencia = ocorrenciaDtoRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Ocorrencia not found with id " + id));
        return ocorrenciaMapper.toDTO(ocorrencia);
    }
}
