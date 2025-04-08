package com.unicamp.navable_api.api;

import com.unicamp.navable_api.api.impl.EstabelecimentoControllerImpl;
import com.unicamp.navable_api.api.model.EstabelecimentoDTO;
import com.unicamp.navable_api.services.impl.EstabelecimentoService;
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

class EstabelecimentoControllerTest {

    @InjectMocks
    private EstabelecimentoControllerImpl estabelecimentoController;

    @Mock
    private EstabelecimentoService estabelecimentoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllEstabelecimentosNearby() {
        double latitude = 10.0;
        double longitude = 20.0;
        List<EstabelecimentoDTO> estabelecimentos = Arrays.asList(new EstabelecimentoDTO(), new EstabelecimentoDTO());
        when(estabelecimentoService.getAllEstabelecimentosNearby(anyDouble(), anyDouble())).thenReturn(estabelecimentos);

        ResponseEntity<List<EstabelecimentoDTO>> response = estabelecimentoController.getAllEstabelecimentosNearby(latitude, longitude);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(estabelecimentos, response.getBody());
        verify(estabelecimentoService, times(1)).getAllEstabelecimentosNearby(latitude, longitude);
    }

    @Test
    void testGetEstabelecimentoById() {
        Integer id = 1;
        EstabelecimentoDTO estabelecimento = new EstabelecimentoDTO();
        when(estabelecimentoService.getEstabelecimentoById(anyInt())).thenReturn(estabelecimento);

        ResponseEntity<EstabelecimentoDTO> response = estabelecimentoController.getEstabelecimentoById(id);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(estabelecimento, response.getBody());
        verify(estabelecimentoService, times(1)).getEstabelecimentoById(id);
    }

    @Test
    void testGetEstabelecimentosByTipo() {
        Float nota = 4.5f;
        List<Integer> categorias = Arrays.asList(1, 2, 3);
        Integer tipoId = 1;
        List<EstabelecimentoDTO> estabelecimentos = Arrays.asList(new EstabelecimentoDTO(), new EstabelecimentoDTO());
        when(estabelecimentoService.filtrar(any(Float.class), any(List.class), anyInt())).thenReturn(estabelecimentos);

        ResponseEntity<List<EstabelecimentoDTO>> response = estabelecimentoController.getEstabelecimentosByTipo(nota, categorias, tipoId);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(estabelecimentos, response.getBody());
        verify(estabelecimentoService, times(1)).filtrar(nota, categorias, tipoId);
    }
}
