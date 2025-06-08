package com.unicamp.navable_api.services;

import com.unicamp.navable_api.api.model.*;
import com.unicamp.navable_api.persistance.entities.*;
import com.unicamp.navable_api.persistance.repositories.*;
import com.unicamp.navable_api.services.impl.OcorrenciaService;
import com.unicamp.navable_api.services.mappers.OcorrenciaMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OcorrenciaServiceTest {
    @Mock
    private OcorrenciaRepository ocorrenciaRepository;

    @Mock
    private TipoOcorrenciaRepository tipoOcorrenciaRepository;

    @InjectMocks
    private OcorrenciaService ocorrenciaService;

    @Test
    void testCreateOcorrencia_invalidDetails() {
        OcorrenciaDTO ocorrenciaDTO = new OcorrenciaDTO();
        ocorrenciaDTO.setIdTipoOcorrencia(null);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            ocorrenciaService.createOcorrencia(ocorrenciaDTO);
        });

        assertTrue(exception.getMessage().contains("Invalid Ocorrencia details provided"));
    }

    @Test
    void testCreateOcorrencia_success() {
        OcorrenciaDTO ocorrenciaDTO = new OcorrenciaDTO();
        ocorrenciaDTO.setIdOcorrencia(1);
        ocorrenciaDTO.setIdTipoOcorrencia(1);
        ocorrenciaDTO.setLongitude(1.0);
        ocorrenciaDTO.setLatitude(1.0);

        Ocorrencia ocorrenciaEntity = OcorrenciaMapper.INSTANCE.toEntity(ocorrenciaDTO);

        when(ocorrenciaRepository.save(any(Ocorrencia.class))).thenReturn(ocorrenciaEntity);

        OcorrenciaDTO result = ocorrenciaService.createOcorrencia(ocorrenciaDTO);

        assertNotNull(result);
        assertEquals(1, result.getIdOcorrencia());
    }

    @Test
    void testGetAllOcorrencias_noResults() {
        when(ocorrenciaRepository.findNearby(10.0, 20.0)).thenReturn(List.of());

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            ocorrenciaService.getAllOcorrencias(10.0, 20.0);
        });

        assertTrue(exception.getMessage().contains("No Ocorrencias found near the provided location"));
    }

    @Test
    void testGetOcorrenciaById_invalidId() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            ocorrenciaService.getOcorrenciaById(-1);  // Invalid ID
        });

        assertTrue(exception.getMessage().contains("Invalid Ocorrencia ID"));
    }

    @Test
    void testGetOcorrenciaById_notFound() {
        when(ocorrenciaRepository.findById(99)).thenReturn(Optional.empty());

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            ocorrenciaService.getOcorrenciaById(99);
        });

        assertTrue(exception.getMessage().contains("Ocorrencia not found with id"));
    }

    @Test
    void testGetOcorrenciaById_success() {
        Ocorrencia ocorrenciaEntity = new Ocorrencia();
        ocorrenciaEntity.setIdOcorrencia(1);
        ocorrenciaEntity.setIdTipoOcorrencia(1);

        when(ocorrenciaRepository.findById(1)).thenReturn(Optional.of(ocorrenciaEntity));

        OcorrenciaDTO result = ocorrenciaService.getOcorrenciaById(1);

        assertNotNull(result);
        assertEquals(1, result.getIdTipoOcorrencia());
    }

    @Test
    void testGetAllTypes_success() {
        TipoOcorrencia tipoOcorrencia1 = new TipoOcorrencia();
        tipoOcorrencia1.setIdTipoOcorrencia(1);
        tipoOcorrencia1.setNome("Tipo 1");

        TipoOcorrencia tipoOcorrencia2 = new TipoOcorrencia();
        tipoOcorrencia2.setIdTipoOcorrencia(2);
        tipoOcorrencia2.setNome("Tipo 2");

        when(tipoOcorrenciaRepository.findAll()).thenReturn(List.of(tipoOcorrencia1, tipoOcorrencia2));

        List<TipoOcorrenciaDTO> result = ocorrenciaService.getAllTypes();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Tipo 1", result.get(0).getNome());
    }
}
