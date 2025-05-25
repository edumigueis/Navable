package com.unicamp.navable_api.services;

import com.unicamp.navable_api.api.model.UsuarioDTO;
import com.unicamp.navable_api.persistance.entities.Usuario;
import com.unicamp.navable_api.persistance.repositories.UsuarioRepository;
import com.unicamp.navable_api.services.exceptions.CredencialesInvalidasException;
import com.unicamp.navable_api.services.exceptions.UsuarioNoEncontradoException;
import com.unicamp.navable_api.services.impl.*;
import com.unicamp.navable_api.services.mappers.UsuarioMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    
    @Mock
private AuthService authService;

    @InjectMocks
    private UsuarioService usuarioService;

    

    @Test
    void testCreateUsuario() {
        // Arrange
        UsuarioDTO dto = new UsuarioDTO();
        dto.setIdUsuario(1);
        dto.setNome("Alex");
        dto.setEmail("alex@teste.com");
        dto.setSenha("123");

        Usuario entity = UsuarioMapper.INSTANCE.toEntity(dto);

        when(usuarioRepository.save(any(Usuario.class))).thenReturn(entity);

        // Act
        UsuarioDTO result = usuarioService.createUsuario(dto);

        // Assert
        assertNotNull(result);
        assertEquals("Alex", result.getNome());
        assertEquals("alex@teste.com", result.getEmail());
    }

    @Test
    void testGetUsuarioById_found() {
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(1);
        usuario.setNome("Alex");

        when(usuarioRepository.findById(1)).thenReturn(Optional.of(usuario));

        UsuarioDTO result = usuarioService.getUsuarioById(1);

        assertNotNull(result);
        assertEquals(1, result.getIdUsuario());
        assertEquals("Alex", result.getNome());
    }

    @Test
    void testGetUsuarioById_notFound() {
        when(usuarioRepository.findById(99)).thenReturn(Optional.empty());

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            usuarioService.getUsuarioById(99);
        });

        assertTrue(exception.getMessage().contains("Usuario not found with id"));
    }

    @Test
    void testAuthenticate_success() throws CredencialesInvalidasException, UsuarioNoEncontradoException {
        Usuario usuario = new Usuario();
        usuario.setEmail("alex@teste.com");
        usuario.setSenha("abc123"); // contraseña guardada

        when(usuarioRepository.findByEmail("alex@teste.com")).thenReturn(Optional.of(usuario));

        UsuarioDTO usuarioDTO = authService.authenticate("alex@teste.com", "abc123").getUsuario();

        assertNotNull(usuarioDTO);
        assertEquals("alex@teste.com", usuarioDTO.getEmail());
    }

    @Test
    void testAuthenticate_wrongPassword() {
        Usuario usuario = new Usuario();
        usuario.setEmail("alex@teste.com");
        usuario.setSenha("abc123");

        when(usuarioRepository.findByEmail("alex@teste.com")).thenReturn(Optional.of(usuario));

        assertThrows(CredencialesInvalidasException.class, () -> {
            authService.authenticate("alex@teste.com", "wrongpass");
        });
    }

    @Test
    void testAuthenticate_userNotFound() {
        when(usuarioRepository.findByEmail("naoexiste@teste.com")).thenReturn(Optional.empty());

        assertThrows(UsuarioNoEncontradoException.class, () -> {
            authService.authenticate("naoexiste@teste.com", "senha");
        });
    }

    @Test
    void testAddCategoriaToUsuario_callsRepository() {
        List<Integer> categorias = List.of(1, 2, 3);

        usuarioService.addCategoriaToUsuario(10, categorias);

        verify(usuarioRepository, times(1)).addCategoriaToUsuario(10, 1);
        verify(usuarioRepository, times(1)).addCategoriaToUsuario(10, 2);
        verify(usuarioRepository, times(1)).addCategoriaToUsuario(10, 3);
    }
}
