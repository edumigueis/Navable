package com.unicamp.navable_api.api;

import com.unicamp.navable_api.api.impl.UsuarioControllerImpl;
import com.unicamp.navable_api.api.model.*;
import com.unicamp.navable_api.services.impl.*;
import org.junit.jupiter.api.*;
import org.mockito.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class UsuarioControllerTest {

    @InjectMocks
    private UsuarioControllerImpl usuarioController;

    @Mock
    private UsuarioService usuarioService;

    @Mock
    private AuthService authService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllUsuarios() {
        List<UsuarioDTO> usuarios = Arrays.asList(new UsuarioDTO(), new UsuarioDTO());
        when(usuarioService.getAllUsuarios()).thenReturn(usuarios);

        List<UsuarioDTO> response = usuarioController.getAllUsuarios();

        assertEquals(usuarios, response);
        verify(usuarioService, times(1)).getAllUsuarios();
    }

    @Test
    void testGetUsuarioById() {
        Integer userId = 1;
        UsuarioDTO usuario = new UsuarioDTO();
        when(usuarioService.getUsuarioById(anyInt())).thenReturn(usuario);

        UsuarioDTO response = usuarioController.getUsuarioById(userId);

        assertEquals(usuario, response);
        verify(usuarioService, times(1)).getUsuarioById(userId);
    }

    @Test
    void testDeleteUsuario() {
        Integer userId = 1;

        usuarioController.deleteUsuario(userId);

        verify(usuarioService, times(1)).deleteUsuario(userId);
    }

    @Test
    void testCreateUsuario() {
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        when(usuarioService.createUsuario(any(UsuarioDTO.class))).thenReturn(usuarioDTO);

        UsuarioDTO response = usuarioController.createUsuario(usuarioDTO);

        assertEquals(usuarioDTO, response);
        verify(usuarioService, times(1)).createUsuario(usuarioDTO);
    }

    @Test
    void testGetMyProfile() {
        Integer userId = 1;
        UsuarioDTO usuario = new UsuarioDTO();
        when(usuarioService.getUsuarioById(userId)).thenReturn(usuario);

        UsuarioDTO response = usuarioController.getMyProfile(userId);

        assertEquals(usuario, response);
        verify(usuarioService, times(1)).getUsuarioById(userId);
    }

    @Test
    void testGetMySelos() {
        int userId = 1;
        List<SeloDTO> selos = Arrays.asList(new SeloDTO(), new SeloDTO());

        when(usuarioService.getSelosByUserId(userId)).thenReturn(selos);

        List<SeloDTO> response = usuarioController.getMySelos(userId);

        assertEquals(selos, response);
        verify(usuarioService, times(1)).getSelosByUserId(userId);
    }

    @Test
    void testGetMyCategorias() {
        int userId = 1;
        List<CategoriaAcessibilidadeDTO> categorias = Arrays.asList(new CategoriaAcessibilidadeDTO(), new CategoriaAcessibilidadeDTO());

        when(usuarioService.getCategoriasByUserId(userId)).thenReturn(categorias);

        List<CategoriaAcessibilidadeDTO> response = usuarioController.getMyCategorias(userId);

        assertEquals(categorias, response);
        verify(usuarioService, times(1)).getCategoriasByUserId(userId);
    }

    @Test
    void testAddSelo() {
        int userId = 1;
        int seloId = 10;

        usuarioController.addSelo(userId, seloId);

        verify(usuarioService, times(1)).addSeloToUsuario(userId, seloId);
    }

    @Test
    void testVote() {
        int userId = 1;
        int ocorrenciaId = 20;

        usuarioController.vote(userId, ocorrenciaId);

        verify(usuarioService, times(1)).voteOnOcorrencia(userId, ocorrenciaId);
    }

    @Test
    void testAddCategoria() {
        int userId = 1;
        List<Integer> categoriaIds = Arrays.asList(1, 2, 3);

        usuarioController.addCategoria(userId, categoriaIds);

        verify(usuarioService, times(1)).addCategoriaToUsuario(userId, categoriaIds);
    }

    @Test
    void testUpdateCategorias() {
        int userId = 1;
        List<Integer> categoriaIds = Arrays.asList(4, 5);

        usuarioController.updateCategorias(userId, categoriaIds);

        verify(usuarioService, times(1)).updateCategoriasToUsuario(userId, categoriaIds);
    }
}