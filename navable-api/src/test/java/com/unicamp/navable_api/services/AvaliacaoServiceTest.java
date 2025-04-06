package com.unicamp.navable_api.services;

import com.unicamp.navable_api.api.model.AvaliacaoDTO;
import com.unicamp.navable_api.persistance.entities.Avaliacao;
import com.unicamp.navable_api.persistance.repositories.AvaliacaoRepository;
import com.unicamp.navable_api.services.impl.AvaliacaoService;
import com.unicamp.navable_api.services.mappers.AvaliacaoMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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
        avaliacaoDTO.setTimestamp(new Date());

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
        avaliacao.setTimestamp(new Date());

        when(avaliacaoRepository.findById(1)).thenReturn(Optional.of(avaliacao));

        // Act
        AvaliacaoDTO result = avaliacaoService.getAvaliacaoById(1);

        // Assert
        assertEquals(1, result.getIdAvaliacao()); // <- Corregido
        assertEquals("Ótimo", result.getAvaliacao());
        verify(avaliacaoRepository, times(1)).findById(1);
    }
}
