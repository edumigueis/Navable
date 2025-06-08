package com.unicamp.navable_api.services;

import com.unicamp.navable_api.api.model.EstabelecimentoDTO;
import com.unicamp.navable_api.persistance.entities.Estabelecimento;
import com.unicamp.navable_api.persistance.repositories.EstabelecimentoRepository;
import com.unicamp.navable_api.persistance.support.GeoLocationSupport;
import com.unicamp.navable_api.services.impl.EstabelecimentoService;
import com.unicamp.navable_api.services.mappers.EstabelecimentoMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EstabelecimentoServiceTest {

    @Mock
    private EstabelecimentoRepository estabelecimentoRepository;

    @InjectMocks
    private EstabelecimentoService estabelecimentoService;

    @Test
    void testMapToDTO() {
        Estabelecimento est1 = new Estabelecimento();
        est1.setIdEstabelecimento(1);
        est1.setIdTipoEstabeleci(2);
        est1.setNome("Estabelecimento A");
        est1.setLatitude(10.0);
        est1.setLongitude(20.0);
        est1.setImagem("imagemA.jpg");
        est1.setEndereco("Rua A");

        Estabelecimento est2 = new Estabelecimento();
        est2.setIdEstabelecimento(2);
        est2.setIdTipoEstabeleci(3);
        est2.setNome("Estabelecimento B");
        est2.setLatitude(15.0);
        est2.setLongitude(25.0);
        est2.setImagem("imagemB.jpg");
        est2.setEndereco("Rua B");

        List<Estabelecimento> estabelecimentos = Arrays.asList(est1, est2);
        EstabelecimentoMapper mapper = EstabelecimentoMapper.INSTANCE;
        List<EstabelecimentoDTO> dtos = estabelecimentos.stream()
                .map(mapper::toDTO)
                .toList();

        assertEquals(2, dtos.size());

        EstabelecimentoDTO dto1 = dtos.get(0);
        EstabelecimentoDTO dto2 = dtos.get(1);

        assertEquals(dto1.getIdEstabelecimento(), est1.getIdEstabelecimento());
        assertEquals(dto2.getIdEstabelecimento(), est2.getIdEstabelecimento());
    }


    @Test
    void testCreateEstabelecimento_invalidDetails() {
        EstabelecimentoDTO estabelecimentoDTO = new EstabelecimentoDTO();
        estabelecimentoDTO.setNome("");  // Invalid name

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            estabelecimentoService.createEstabelecimento(estabelecimentoDTO);
        });

        assertTrue(exception.getMessage().contains("Invalid Estabelecimento details provided"));
    }

    @Test
    void testCreateEstabelecimento_success() {
        EstabelecimentoDTO estabelecimentoDTO = new EstabelecimentoDTO();
        estabelecimentoDTO.setNome("Estabelecimento 1");

        Estabelecimento estabelecimentoEntity = EstabelecimentoMapper.INSTANCE.toEntity(estabelecimentoDTO);

        when(estabelecimentoRepository.save(any(Estabelecimento.class))).thenReturn(estabelecimentoEntity);

        EstabelecimentoDTO result = estabelecimentoService.createEstabelecimento(estabelecimentoDTO);

        assertNotNull(result);
        assertEquals("Estabelecimento 1", result.getNome());
    }

    @Test
    void testGetAllEstabelecimentosNearby_noResults() {
        when(estabelecimentoRepository.findNearbyWithRatings(10.0, 20.0, GeoLocationSupport.DEFAULT_SEARCH_RADIUS_KM)).thenReturn(List.of());

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            estabelecimentoService.getAllEstabelecimentosNearby(10.0, 20.0);
        });

        assertTrue(exception.getMessage().contains("No establishments found near the provided location"));
    }

    @Test
    void testGetEstabelecimentoById_invalidId() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            estabelecimentoService.getEstabelecimentoById(-1);  // Invalid ID
        });

        assertTrue(exception.getMessage().contains("Invalid Estabelecimento ID"));
    }

    @Test
    void testGetEstabelecimentoById_notFound() {
        when(estabelecimentoRepository.findById(99)).thenReturn(Optional.empty());

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            estabelecimentoService.getEstabelecimentoById(99);
        });

        assertTrue(exception.getMessage().contains("Estabelecimento not found with id"));
    }

    @Test
    void testGetEstabelecimentoById_success() {
        Estabelecimento estabelecimentoEntity = new Estabelecimento();
        estabelecimentoEntity.setIdEstabelecimento(1);
        estabelecimentoEntity.setNome("Estabelecimento 1");

        when(estabelecimentoRepository.findById(1)).thenReturn(Optional.of(estabelecimentoEntity));
        when(estabelecimentoRepository.findAverageNotaByEstabelecimentoId(1)).thenReturn(4.5);

        EstabelecimentoDTO result = estabelecimentoService.getEstabelecimentoById(1);

        assertNotNull(result);
        assertEquals("Estabelecimento 1", result.getNome());
        assertEquals(4.5, result.getNota());
    }

    @Test
    void testFiltrar_invalidParameters() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            estabelecimentoService.filtrar(null, null, null);  // Invalid parameters
        });

        assertTrue(exception.getMessage().contains("Invalid filter parameters provided"));
    }

    @Test
    void testFiltrar_success() {
        EstabelecimentoDTO estabelecimentoDTO = new EstabelecimentoDTO();
        estabelecimentoDTO.setNome("Estabelecimento 1");

        Estabelecimento estabelecimentoEntity = EstabelecimentoMapper.INSTANCE.toEntity(estabelecimentoDTO);

        when(estabelecimentoRepository.findByNotaAndCategoriaAndTipo(4.5f, List.of(1), 1))
                .thenReturn(List.of(estabelecimentoEntity));

        List<EstabelecimentoDTO> result = estabelecimentoService.filtrar(4.5f, List.of(1), 1);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Estabelecimento 1", result.get(0).getNome());
    }
}
