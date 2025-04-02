package com.unicamp.navable_api.api;

import com.unicamp.navable_api.api.impl.CategoriaAcessibilidadeControllerImpl;
import com.unicamp.navable_api.api.model.CategoriaAcessibilidadeDTO;
import com.unicamp.navable_api.services.impl.CategoriaAcessibilidadeService;
import org.junit.jupiter.api.*;
import org.mockito.*;
import org.springframework.http.ResponseEntity;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class CategoriaAcessibilidadeControllerTest {

    @InjectMocks
    private CategoriaAcessibilidadeControllerImpl categoriaController;

    @Mock
    private CategoriaAcessibilidadeService categoriaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllCategorias() {
        List<CategoriaAcessibilidadeDTO> categorias = Arrays.asList(new CategoriaAcessibilidadeDTO(), new CategoriaAcessibilidadeDTO());
        when(categoriaService.getAllCategorias()).thenReturn(categorias);

        ResponseEntity<List<CategoriaAcessibilidadeDTO>> response = categoriaController.getAllCategorias();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(categorias, response.getBody());
        verify(categoriaService, times(1)).getAllCategorias();
    }

    @Test
    void testGetCategoriaById() {
        Integer id = 1;
        CategoriaAcessibilidadeDTO categoria = new CategoriaAcessibilidadeDTO();
        when(categoriaService.getCategoriaById(anyInt())).thenReturn(categoria);

        CategoriaAcessibilidadeDTO response = categoriaController.getCategoriaById(id);

        assertEquals(categoria, response);
        verify(categoriaService, times(1)).getCategoriaById(id);
    }
}
