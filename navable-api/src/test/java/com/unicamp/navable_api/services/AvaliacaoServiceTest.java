package com.unicamp.navable_api.services;

import com.unicamp.navable_api.api.model.AvaliacaoDTO;
import com.unicamp.navable_api.persistance.repositories.AvaliacaoRepository;
import com.unicamp.navable_api.services.impl.AvaliacaoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AvaliacaoServiceTest {

    @Mock
    private AvaliacaoRepository avaliacaoRepository;

    @InjectMocks
    private AvaliacaoService avaliacaoService;

    @Test
    void testCreateAvaliacao_invalidNota() {
        AvaliacaoDTO dto = new AvaliacaoDTO();
        dto.setNota(6);  // Invalid note, should be between 1 and 5

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            avaliacaoService.createAvaliacao(dto);
        });

        assertTrue(exception.getMessage().contains("Nota must be between 1 and 5"));
    }

    @Test
    void testCreateAvaliacao_invalidEstabelecimentoId() {
        AvaliacaoDTO dto = new AvaliacaoDTO();
        dto.setNota(4);
        dto.setIdEstabelecimento(-1);  // Invalid Estabelecimento ID

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            avaliacaoService.createAvaliacao(dto);
        });

        assertTrue(exception.getMessage().contains("Estabelecimento ID is invalid"));
    }

    @Test
    void testGetAvaliacoesByEstabelecimentoAndFilters_notFound() {
        when(avaliacaoRepository.findByEstabelecimentoIdAndFilters(any(), any(), any(), any()))
                .thenReturn(Collections.emptyList());
        LocalDate curr = LocalDate.now();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            avaliacaoService.getAvaliacoesByEstabelecimentoAndFilters(1, 5, curr, curr);
        });

        assertTrue(exception.getMessage().contains("No avaliacoes found for estabelecimento"));
    }

    @Test
    void testGetAvaliacaoById_notFound() {
        when(avaliacaoRepository.findById(999)).thenReturn(Optional.empty());

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            avaliacaoService.getAvaliacaoById(999);
        });

        assertTrue(exception.getMessage().contains("Avaliacao not found with id"));
    }

    @Test
    void testGetAvaliacaoById_invalidId() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            avaliacaoService.getAvaliacaoById(-1);
        });

        assertTrue(exception.getMessage().contains("Invalid Avaliacao ID"));
    }
}
