package com.unicamp.navable_api.api;

import com.unicamp.navable_api.api.impl.AvaliacaoControllerImpl;
import com.unicamp.navable_api.api.model.AvaliacaoDTO;
import com.unicamp.navable_api.services.impl.AvaliacaoService;
import org.junit.jupiter.api.*;
import org.mockito.*;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class AvaliacaoControllerTest {

    @InjectMocks
    private AvaliacaoControllerImpl avaliacaoController;

    @Mock
    private AvaliacaoService avaliacaoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateAvaliacao() {
        AvaliacaoDTO avaliacaoDTO = new AvaliacaoDTO();
        AvaliacaoDTO createdAvaliacao = new AvaliacaoDTO();
        when(avaliacaoService.createAvaliacao(any(AvaliacaoDTO.class))).thenReturn(createdAvaliacao);

        ResponseEntity<AvaliacaoDTO> response = avaliacaoController.createAvaliacao(avaliacaoDTO);

        assertEquals(201, response.getStatusCodeValue());
        assertEquals(createdAvaliacao, response.getBody());
        verify(avaliacaoService, times(1)).createAvaliacao(avaliacaoDTO);
    }

    @Test
    void testGetAllAvaliacoesByEstabelecimento() {
        Integer estabelecimentoId = 1;
        List<AvaliacaoDTO> avaliacoes = Arrays.asList(new AvaliacaoDTO(), new AvaliacaoDTO());
        when(avaliacaoService.getAvaliacoesByEstabelecimentoAndFilters(anyInt(), anyInt(), any(), any())).thenReturn(avaliacoes);
        LocalDate sampleDate = LocalDate.now();

        ResponseEntity<List<AvaliacaoDTO>> response = avaliacaoController.getAvaliacoesByEstabelecimento(estabelecimentoId, 0, sampleDate, sampleDate);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(avaliacoes, response.getBody());
        verify(avaliacaoService, times(1)).getAvaliacoesByEstabelecimentoAndFilters(estabelecimentoId, 0, sampleDate, sampleDate);
    }

    @Test
    void testGetAvaliacoesByUsuario() {
        Integer usuarioId = 1;
        List<AvaliacaoDTO> avaliacoes = Arrays.asList(new AvaliacaoDTO(), new AvaliacaoDTO());
        when(avaliacaoService.getAvaliacoesByUsuarioAndFilters(anyInt(), anyInt(), any(), any())).thenReturn(avaliacoes);
        LocalDate sampleDate = LocalDate.now();

        ResponseEntity<List<AvaliacaoDTO>> response = avaliacaoController.getAvaliacoesByUsuario(usuarioId, 0, sampleDate, sampleDate);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(avaliacoes, response.getBody());
        verify(avaliacaoService, times(1)).getAvaliacoesByUsuarioAndFilters(usuarioId, 0, sampleDate, sampleDate);
    }

    @Test
    void testGetAvaliacaoById() {
        Integer avaliacaoId = 1;
        AvaliacaoDTO avaliacao = new AvaliacaoDTO();
        when(avaliacaoService.getAvaliacaoById(anyInt())).thenReturn(avaliacao);

        ResponseEntity<AvaliacaoDTO> response = avaliacaoController.getAvaliacaoById(avaliacaoId);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(avaliacao, response.getBody());
        verify(avaliacaoService, times(1)).getAvaliacaoById(avaliacaoId);
    }

    @Test
    void testGetAvaliacoesByEstabelecimentoNotaLimiteInferiorValida() {
        Integer estabelecimentoId = 1;
        Integer nota = 1; // valor limite inferior válido
        LocalDate data = LocalDate.now();
        List<AvaliacaoDTO> avaliacoes = List.of(new AvaliacaoDTO());

        when(avaliacaoService.getAvaliacoesByEstabelecimentoAndFilters(estabelecimentoId, nota, data, data)).thenReturn(avaliacoes);

        ResponseEntity<List<AvaliacaoDTO>> response = avaliacaoController.getAvaliacoesByEstabelecimento(estabelecimentoId, nota, data, data);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(avaliacoes, response.getBody());
        verify(avaliacaoService).getAvaliacoesByEstabelecimentoAndFilters(estabelecimentoId, nota, data, data);
    }

    @Test
    void testGetAvaliacoesByEstabelecimentoNotaLimiteSuperiorValida() {
        Integer estabelecimentoId = 1;
        Integer nota = 5; // valor limite superior válido
        LocalDate data = LocalDate.now();
        List<AvaliacaoDTO> avaliacoes = List.of(new AvaliacaoDTO());

        when(avaliacaoService.getAvaliacoesByEstabelecimentoAndFilters(estabelecimentoId, nota, data, data)).thenReturn(avaliacoes);

        ResponseEntity<List<AvaliacaoDTO>> response = avaliacaoController.getAvaliacoesByEstabelecimento(estabelecimentoId, nota, data, data);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(avaliacoes, response.getBody());
        verify(avaliacaoService).getAvaliacoesByEstabelecimentoAndFilters(estabelecimentoId, nota, data, data);
    }

    @Test
    void testGetAvaliacoesByEstabelecimentoNotaForaDoLimiteInferior() {
        Integer estabelecimentoId = 1;
        Integer notaInvalida = 0; // fora da faixa válida (classe inválida)
        LocalDate data = LocalDate.now();

        when(avaliacaoService.getAvaliacoesByEstabelecimentoAndFilters(estabelecimentoId, notaInvalida, data, data))
                .thenReturn(Collections.emptyList());

        ResponseEntity<List<AvaliacaoDTO>> response = avaliacaoController.getAvaliacoesByEstabelecimento(estabelecimentoId, notaInvalida, data, data);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(Collections.emptyList(), response.getBody());
        verify(avaliacaoService).getAvaliacoesByEstabelecimentoAndFilters(estabelecimentoId, notaInvalida, data, data);
    }

    @Test
    void testGetAvaliacoesByUsuarioNotaForaDoLimiteSuperior() {
        Integer usuarioId = 1;
        Integer notaInvalida = 6; // valor inválido
        LocalDate data = LocalDate.now();

        when(avaliacaoService.getAvaliacoesByUsuarioAndFilters(usuarioId, notaInvalida, data, data))
                .thenReturn(Collections.emptyList());

        ResponseEntity<List<AvaliacaoDTO>> response = avaliacaoController.getAvaliacoesByUsuario(usuarioId, notaInvalida, data, data);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(Collections.emptyList(), response.getBody());
        verify(avaliacaoService).getAvaliacoesByUsuarioAndFilters(usuarioId, notaInvalida, data, data);
    }
}