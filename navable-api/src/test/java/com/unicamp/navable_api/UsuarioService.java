package com.unicamp.navable_api;

import com.unicamp.navable_api.api.model.UsuarioDTO;
import com.unicamp.navable_api.persistance.entities.Usuario;
import com.unicamp.navable_api.persistance.repositories.UsuarioRepository;
import com.unicamp.navable_api.services.impl.UsuarioService;
import com.unicamp.navable_api.services.mappers.UsuarioMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import javax.naming.AuthenticationException;
import java.util.Optional;

import java.util.List;
import java.util.ArrayList;


import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

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
    void testSignIn_success() throws AuthenticationException {
        Usuario usuario = new Usuario();
        usuario.setEmail("alex@teste.com");
        usuario.setSenha("abc123"); // senha armazenada

        when(usuarioRepository.findByEmail("alex@teste.com")).thenReturn(Optional.of(usuario));

        UsuarioDTO result = usuarioService.signIn("alex@teste.com", "abc123");

        assertNotNull(result);
        assertEquals("alex@teste.com", result.getEmail());
    }

    @Test
    void testSignIn_wrongPassword() {
        Usuario usuario = new Usuario();
        usuario.setEmail("alex@teste.com");
        usuario.setSenha("abc123");

        when(usuarioRepository.findByEmail("alex@teste.com")).thenReturn(Optional.of(usuario));

        assertThrows(AuthenticationException.class, () -> {
            usuarioService.signIn("alex@teste.com", "wrongpass");
        });
    }

    @Test
    void testSignIn_userNotFound() {
        when(usuarioRepository.findByEmail("naoexiste@teste.com")).thenReturn(Optional.empty());

        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            usuarioService.signIn("naoexiste@teste.com", "senha");
        });

        assertTrue(ex.getMessage().contains("User not found"));
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
