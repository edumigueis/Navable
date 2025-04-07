package com.unicamp.navable_api.services;

import com.unicamp.navable_api.api.model.AvaliacaoDTO;
import com.unicamp.navable_api.persistance.entities.Avaliacao;
import com.unicamp.navable_api.persistance.repositories.AvaliacaoRepository;
import com.unicamp.navable_api.services.impl.AvaliacaoService;
import com.unicamp.navable_api.services.mappers.AvaliacaoMapper;
import org.junit.jupiter.api.*;
import org.mockito.*;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AvaliacaoServiceTest {

    @Mock
    private AvaliacaoRepository avaliacaoRepository;

    @InjectMocks
    private AvaliacaoService avaliacaoService;

    private AvaliacaoMapper avaliacaoMapper = AvaliacaoMapper.INSTANCE;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateAvaliacao() {
        // Arrange
        AvaliacaoDTO avaliacaoDTO = new AvaliacaoDTO();
        avaliacaoDTO.setIdAvaliacao(1);  // <- Corregido
        avaliacaoDTO.setIdUsuario(2);
        avaliacaoDTO.setIdEstabelecimento(3);
        avaliacaoDTO.setAvaliacao("Muito bom!");
        avaliacaoDTO.setNota(5);
        avaliacaoDTO.setTimestamp(LocalDate.now());

        Avaliacao avaliacao = avaliacaoMapper.toEntity(avaliacaoDTO);
        Avaliacao savedAvaliacao = new Avaliacao();
        savedAvaliacao.setIdAvaliacao(1);
        savedAvaliacao.setAvaliacao("Muito bom!");
        savedAvaliacao.setNota(5);
        savedAvaliacao.setIdUsuario(2);
        savedAvaliacao.setIdEstabelecimento(3);
        savedAvaliacao.setTimestamp(avaliacaoDTO.getTimestamp());

        when(avaliacaoRepository.save(any(Avaliacao.class))).thenReturn(savedAvaliacao);

        // Act
        AvaliacaoDTO result = avaliacaoService.createAvaliacao(avaliacaoDTO);

        // Assert
        assertEquals(avaliacaoDTO.getIdAvaliacao(), result.getIdAvaliacao()); // <- Corregido
        assertEquals(avaliacaoDTO.getNota(), result.getNota());
        verify(avaliacaoRepository, times(1)).save(any(Avaliacao.class));
    }

    @Test
    void testGetAvaliacaoById() {
        // Arrange
        Avaliacao avaliacao = new Avaliacao();
        avaliacao.setIdAvaliacao(1); // <- Corregido
        avaliacao.setAvaliacao("Ótimo");
        avaliacao.setNota(5);
        avaliacao.setIdUsuario(2);
        avaliacao.setIdEstabelecimento(3);
        avaliacao.setTimestamp(LocalDate.now());

        when(avaliacaoRepository.findById(1)).thenReturn(Optional.of(avaliacao));

        // Act
        AvaliacaoDTO result = avaliacaoService.getAvaliacaoById(1);

        // Assert
        assertEquals(1, result.getIdAvaliacao()); // <- Corregido
        assertEquals("Ótimo", result.getAvaliacao());
        verify(avaliacaoRepository, times(1)).findById(1);
    }
}
