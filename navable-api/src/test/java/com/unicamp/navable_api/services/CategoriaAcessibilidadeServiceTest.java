package com.unicamp.navable_api.services;

import com.unicamp.navable_api.api.model.CategoriaAcessibilidadeDTO;
import com.unicamp.navable_api.persistance.entities.CategoriaAcessibilidade;
import com.unicamp.navable_api.persistance.repositories.CategoriaAcessibilidadeRepository;
import com.unicamp.navable_api.services.impl.CategoriaAcessibilidadeService;
import com.unicamp.navable_api.services.mappers.CategoriaAcessibilidadeMapper;
import org.junit.jupiter.api.*;
import org.mockito.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class CategoriaAcessibilidadeServiceTest {

    @Mock
    private CategoriaAcessibilidadeRepository categoriaRepository;

    @InjectMocks
    private CategoriaAcessibilidadeService categoriaService;

    private static final CategoriaAcessibilidadeMapper categoriaMapper = CategoriaAcessibilidadeMapper.INSTANCE;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllCategorias() {
        CategoriaAcessibilidade categoria1 = new CategoriaAcessibilidade();
        categoria1.setCategoriaAcId(1);
        categoria1.setNome("Rampa");
        categoria1.setGrupo("Mobilidade");

        CategoriaAcessibilidade categoria2 = new CategoriaAcessibilidade();
        categoria2.setCategoriaAcId(2);
        categoria2.setNome("Elevador");
        categoria2.setGrupo("Mobilidade");

        when(categoriaRepository.findAll()).thenReturn(Arrays.asList(categoria1, categoria2));

        var resultado = categoriaService.getAllCategorias();

        assertEquals(2, resultado.size());
        assertEquals("Rampa", resultado.get(0).getNome());
        verify(categoriaRepository, times(1)).findAll();
    }

    @Test
    void testGetCategoriaById() {
        CategoriaAcessibilidade categoria = new CategoriaAcessibilidade();
        categoria.setCategoriaAcId(1);
        categoria.setNome("Banheiro acessível");
        categoria.setGrupo("Infraestrutura");

        when(categoriaRepository.findById(1)).thenReturn(Optional.of(categoria));

        CategoriaAcessibilidadeDTO resultado = categoriaService.getCategoriaById(1);

        assertNotNull(resultado);
        assertEquals("Banheiro acessível", resultado.getNome());
        verify(categoriaRepository, times(1)).findById(1);
    }

    @Test
    void testGetAllCategorias_noCategoriesFound() {
        when(categoriaRepository.findAll()).thenReturn(Collections.emptyList());

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            categoriaService.getAllCategorias();
        });

        assertTrue(exception.getMessage().contains("No categories found"));
    }

    @Test
    void testGetCategoriaById_invalidId() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            categoriaService.getCategoriaById(-1);  // Invalid ID
        });

        assertTrue(exception.getMessage().contains("Invalid Categoria ID"));
    }

    @Test
    void testGetCategoriaById_notFound() {
        when(categoriaRepository.findById(99)).thenReturn(Optional.empty());

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            categoriaService.getCategoriaById(99);
        });

        assertTrue(exception.getMessage().contains("Categoria not found with id"));
    }

    @Test
    void testUpdateCategoria_invalidId() {
        CategoriaAcessibilidadeDTO categoriaDTO = new CategoriaAcessibilidadeDTO();
        categoriaDTO.setNome("Categoria 1");

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            categoriaService.updateCategoria(-1, categoriaDTO);  // Invalid ID
        });

        assertTrue(exception.getMessage().contains("Invalid Categoria ID"));
    }

    @Test
    void testUpdateCategoria_nameNull() {
        CategoriaAcessibilidadeDTO categoriaDTO = new CategoriaAcessibilidadeDTO();
        categoriaDTO.setNome(null);  // Invalid name

        assertThrows(IllegalArgumentException.class, () -> {
            categoriaService.updateCategoria(1, categoriaDTO);  // Valid ID, invalid name
        });
    }

    @Test
    void testUpdateCategoria_notFound() {
        CategoriaAcessibilidadeDTO categoriaDTO = new CategoriaAcessibilidadeDTO();
        categoriaDTO.setNome("Categoria 1");

        when(categoriaRepository.findById(99)).thenReturn(Optional.empty());

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            categoriaService.updateCategoria(99, categoriaDTO);
        });

        assertTrue(exception.getMessage().contains("Categoria not found with id"));
    }

    @Test
    void testUpdateCategoria_success() {
        CategoriaAcessibilidadeDTO categoriaDTO = new CategoriaAcessibilidadeDTO();
        categoriaDTO.setNome("Categoria 1");
        categoriaDTO.setGrupo("Grupo 1");

        CategoriaAcessibilidade categoriaEntity = new CategoriaAcessibilidade();
        categoriaEntity.setCategoriaAcId(1);
        categoriaEntity.setNome("Old Name");
        categoriaEntity.setGrupo("Old Group");

        when(categoriaRepository.findById(1)).thenReturn(Optional.of(categoriaEntity));
        when(categoriaRepository.save(any(CategoriaAcessibilidade.class))).thenReturn(categoriaEntity);

        CategoriaAcessibilidadeDTO updatedCategoria = categoriaService.updateCategoria(1, categoriaDTO);

        assertNotNull(updatedCategoria);
        assertEquals("Categoria 1", updatedCategoria.getNome());
        assertEquals("Grupo 1", updatedCategoria.getGrupo());
    }

}
