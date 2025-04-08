package com.unicamp.navable_api.api;

import com.unicamp.navable_api.api.impl.OcorrenciaControllerImpl;
import com.unicamp.navable_api.api.model.*;
import com.unicamp.navable_api.services.impl.OcorrenciaService;
import org.junit.jupiter.api.*;
import org.mockito.*;
import org.springframework.http.ResponseEntity;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class OcorrenciaControllerTest {

    @InjectMocks
    private OcorrenciaControllerImpl ocorrenciaController;

    @Mock
    private OcorrenciaService ocorrenciaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllTypes() {
        List<TipoOcorrenciaDTO> tipos = Arrays.asList(new TipoOcorrenciaDTO(), new TipoOcorrenciaDTO());
        when(ocorrenciaService.getAllTypes()).thenReturn(tipos);

        ResponseEntity<List<TipoOcorrenciaDTO>> response = ocorrenciaController.getAllTypes();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(tipos, response.getBody());
        verify(ocorrenciaService, times(1)).getAllTypes();
    }

    @Test
    void testGetAllOcorrencias() {
        double latitude = 10.0;
        double longitude = 20.0;
        List<OcorrenciaDTO> ocorrencias = Arrays.asList(new OcorrenciaDTO(), new OcorrenciaDTO());
        when(ocorrenciaService.getAllOcorrencias(anyDouble(), anyDouble())).thenReturn(ocorrencias);

        ResponseEntity<List<OcorrenciaDTO>> response = ocorrenciaController.getAllOcorrencias(latitude, longitude);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(ocorrencias, response.getBody());
        verify(ocorrenciaService, times(1)).getAllOcorrencias(latitude, longitude);
    }

    @Test
    void testGetOcorrenciaById() {
        Integer id = 1;
        OcorrenciaDTO ocorrencia = new OcorrenciaDTO();
        when(ocorrenciaService.getOcorrenciaById(anyInt())).thenReturn(ocorrencia);

        ResponseEntity<OcorrenciaDTO> response = ocorrenciaController.getOcorrenciaById(id);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(ocorrencia, response.getBody());
        verify(ocorrenciaService, times(1)).getOcorrenciaById(id);
    }

    @Test
    void testCreateOcorrencia() {
        OcorrenciaDTO ocorrenciaDTO = new OcorrenciaDTO();
        OcorrenciaDTO createdOcorrencia = new OcorrenciaDTO();
        when(ocorrenciaService.createOcorrencia(any(OcorrenciaDTO.class))).thenReturn(createdOcorrencia);

        ResponseEntity<OcorrenciaDTO> response = ocorrenciaController.createOcorrencia(ocorrenciaDTO);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(createdOcorrencia, response.getBody());
        verify(ocorrenciaService, times(1)).createOcorrencia(ocorrenciaDTO);
    }
}