package com.unicamp.navable_api.api;

import com.unicamp.navable_api.api.impl.UsuarioControllerImpl;
import com.unicamp.navable_api.api.model.*;
import com.unicamp.navable_api.api.model.auth.LoginResponse;
import com.unicamp.navable_api.services.impl.*;
import org.junit.jupiter.api.*;
import org.mockito.*;
import org.springframework.http.ResponseEntity;


import com.unicamp.navable_api.services.exceptions.CredencialesInvalidasException;
import com.unicamp.navable_api.services.exceptions.UsuarioNoEncontradoException;


import javax.naming.AuthenticationException;
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
    void testGetAllSelosByUsuario() {
        Integer userId = 1;
        List<SeloDTO> selos = Arrays.asList(new SeloDTO(), new SeloDTO());
        when(usuarioService.getSelosByUserId(anyInt())).thenReturn(selos);

        List<SeloDTO> response = usuarioController.getAllSelosByUsuario(userId);

        assertEquals(selos, response);
        verify(usuarioService, times(1)).getSelosByUserId(userId);
    }

    @Test
    void testGetAllCategoriasByUsuario() {
        Integer userId = 1;
        List<CategoriaAcessibilidadeDTO> categorias = Arrays.asList(new CategoriaAcessibilidadeDTO(), new CategoriaAcessibilidadeDTO());
        when(usuarioService.getCategoriasByUserId(anyInt())).thenReturn(categorias);

        List<CategoriaAcessibilidadeDTO> response = usuarioController.getAllCategoriasByUsuario(userId);

        assertEquals(categorias, response);
        verify(usuarioService, times(1)).getCategoriasByUserId(userId);
    }

@Test
    void testSignIn() {
        String email = "test@example.com";
        String password = "password";
        UsuarioDTO usuarioDTO = new UsuarioDTO();

        when(authService.authenticate(email, password)).thenReturn(new LoginResponse(usuarioDTO, "token"));

        ResponseEntity<UsuarioDTO> response = usuarioController.signIn(email, password);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(usuarioDTO, response.getBody());

        verify(authService, times(1)).authenticate(email, password);
    }

    @Test
    void testSignInCredencialesInvalidas() {
        String email = "test@test.com";
        String password = "wrongpassword";

        
        when(authService.authenticate(email, password)).thenThrow(new CredencialesInvalidasException());

        ResponseEntity<UsuarioDTO> response = usuarioController.signIn(email, password);

        assertEquals(403, response.getStatusCodeValue());

        verify(authService, times(1)).authenticate(email, password);
    }

    @Test
    void testSignInUsuarioNoEncontrado() {
        String email = "notfound@example.com";
        String password = "password";

        when(authService.authenticate(email, password)).thenThrow(new UsuarioNoEncontradoException(email));

        ResponseEntity<UsuarioDTO> response = usuarioController.signIn(email, password);

        assertEquals(403, response.getStatusCodeValue());

        verify(authService, times(1)).authenticate(email, password);
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
    void testCreateUsuario() {
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        when(usuarioService.createUsuario(any(UsuarioDTO.class))).thenReturn(usuarioDTO);

        UsuarioDTO response = usuarioController.createUsuario(usuarioDTO);

        assertEquals(usuarioDTO, response);
        verify(usuarioService, times(1)).createUsuario(usuarioDTO);
    }

    @Test
    void testDeleteUsuario() {
        Integer userId = 1;

        usuarioController.deleteUsuario(userId);

        verify(usuarioService, times(1)).deleteUsuario(userId);
    }

    @Test
    void testAddSeloToUsuario() {
        Integer usuarioId = 1;
        Integer seloId = 2;

        usuarioController.addSeloToUsuario(usuarioId, seloId);

        verify(usuarioService, times(1)).addSeloToUsuario(usuarioId, seloId);
    }

    @Test
    void testVoteOnOcorrencia() {
        Integer usuarioId = 1;
        Integer ocorrenciaId = 2;

        usuarioController.voteOnOcorrencia(usuarioId, ocorrenciaId);

        verify(usuarioService, times(1)).voteOnOcorrencia(usuarioId, ocorrenciaId);
    }

    @Test
    void testAddCategoriaToUsuario() {
        Integer usuarioId = 1;
        List<Integer> categoriaIds = Arrays.asList(1, 2, 3);

        usuarioController.addCategoriaToUsuario(usuarioId, categoriaIds);

        verify(usuarioService, times(1)).addCategoriaToUsuario(usuarioId, categoriaIds);
    }
}
