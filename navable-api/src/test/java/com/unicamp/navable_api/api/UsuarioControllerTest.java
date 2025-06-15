package com.unicamp.navable_api.api;

import com.unicamp.navable_api.api.impl.UsuarioControllerImpl;
import com.unicamp.navable_api.api.model.*;
import com.unicamp.navable_api.services.impl.*;
import org.junit.jupiter.api.*;
import org.mockito.*;
import org.springframework.security.oauth2.jwt.Jwt;

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

    private Jwt mockJwtWithUserId(int id, String email) {
        return Jwt.withTokenValue("mock-token")
                .header("alg", "none")
                .claims(claims -> {
                    claims.put("id", id);
                    claims.put("sub", email);
                }) // ✅ correct use of Consumer
                .build();
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
        String email = "user@example.com";
        Jwt jwt = mockJwtWithUserId(1, email);
        UsuarioDTO usuario = new UsuarioDTO();

        when(usuarioService.getUsuarioByEmail(email)).thenReturn(usuario);

        UsuarioDTO response = usuarioController.getMyProfile(jwt);

        assertEquals(usuario, response);
        verify(usuarioService, times(1)).getUsuarioByEmail(email);
    }

    @Test
    void testGetMySelos() {
        int userId = 1;
        Jwt jwt = mockJwtWithUserId(userId, "user@example.com");
        List<SeloDTO> selos = Arrays.asList(new SeloDTO(), new SeloDTO());

        when(usuarioService.getSelosByUserId(userId)).thenReturn(selos);

        List<SeloDTO> response = usuarioController.getMySelos(jwt);

        assertEquals(selos, response);
        verify(usuarioService, times(1)).getSelosByUserId(userId);
    }

    @Test
    void testGetMyCategorias() {
        int userId = 1;
        Jwt jwt = mockJwtWithUserId(userId, "user@example.com");
        List<CategoriaAcessibilidadeDTO> categorias = Arrays.asList(new CategoriaAcessibilidadeDTO(), new CategoriaAcessibilidadeDTO());

        when(usuarioService.getCategoriasByUserId(userId)).thenReturn(categorias);

        List<CategoriaAcessibilidadeDTO> response = usuarioController.getMyCategorias(jwt);

        assertEquals(categorias, response);
        verify(usuarioService, times(1)).getCategoriasByUserId(userId);
    }

    @Test
    void testAddSelo() {
        int userId = 1;
        int seloId = 10;
        Jwt jwt = mockJwtWithUserId(userId, "user@example.com");

        usuarioController.addSelo(jwt, seloId);

        verify(usuarioService, times(1)).addSeloToUsuario(userId, seloId);
    }

    @Test
    void testVote() {
        int userId = 1;
        int ocorrenciaId = 20;
        Jwt jwt = mockJwtWithUserId(userId, "user@example.com");

        usuarioController.vote(jwt, ocorrenciaId);

        verify(usuarioService, times(1)).voteOnOcorrencia(userId, ocorrenciaId);
    }

    @Test
    void testAddCategoria() {
        int userId = 1;
        List<Integer> categoriaIds = Arrays.asList(1, 2, 3);
        Jwt jwt = mockJwtWithUserId(userId, "user@example.com");

        usuarioController.addCategoria(jwt, categoriaIds);

        verify(usuarioService, times(1)).addCategoriaToUsuario(userId, categoriaIds);
    }

    @Test
    void testUpdateCategorias() {
        int userId = 1;
        List<Integer> categoriaIds = Arrays.asList(4, 5);
        Jwt jwt = mockJwtWithUserId(userId, "user@example.com");

        usuarioController.updateCategorias(jwt, categoriaIds);

        verify(usuarioService, times(1)).updateCategoriasToUsuario(userId, categoriaIds);
    }
}
