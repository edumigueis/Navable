package com.unicamp.navable_api.services;

import com.unicamp.navable_api.api.model.*;
import com.unicamp.navable_api.persistance.entities.*;
import com.unicamp.navable_api.persistance.repositories.UsuarioRepository;
import com.unicamp.navable_api.services.impl.UsuarioService;
import com.unicamp.navable_api.services.mappers.UsuarioMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioService usuarioService;

    @Test
    void testCreateUsuario_validData() {
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
        assertEquals(1, result.getIdUsuario());
    }

    @Test
    void testCreateUsuario_invalidEmail() {
        // Arrange
        UsuarioDTO dto = new UsuarioDTO();
        dto.setIdUsuario(1);
        dto.setNome("Alex");
        dto.setEmail("invalid-email");  // Email inválido
        dto.setSenha("123");

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            usuarioService.createUsuario(dto);
        });

        assertTrue(exception.getMessage().contains("Invalid email"));
    }

    @Test
    void testCreateUsuario_invalidPassword() {
        // Arrange
        UsuarioDTO dto = new UsuarioDTO();
        dto.setIdUsuario(1);
        dto.setNome("Alex");
        dto.setEmail("alex@teste.com");
        dto.setSenha("");  // Senha vazia, inválida

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            usuarioService.createUsuario(dto);
        });

        assertTrue(exception.getMessage().contains("Password is too short"));
    }

    @Test
    void testCreateUsuario_invalidName() {
        // Arrange
        UsuarioDTO dto = new UsuarioDTO();
        dto.setIdUsuario(1);
        dto.setNome("");  // Nome vazio
        dto.setEmail("alex@teste.com");
        dto.setSenha("123");

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            usuarioService.createUsuario(dto);
        });

        assertTrue(exception.getMessage().contains("Name cannot be empty"));
    }

    @Test
    void testGetUsuarioById_found() {
        // Arrange
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(1);
        usuario.setNome("Alex");

        when(usuarioRepository.findById(1)).thenReturn(Optional.of(usuario));

        // Act
        UsuarioDTO result = usuarioService.getUsuarioById(1);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getIdUsuario());
        assertEquals("Alex", result.getNome());
    }

    @Test
    void testGetUsuarioById_notFound() {
        // Arrange
        when(usuarioRepository.findById(99)).thenReturn(Optional.empty());

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            usuarioService.getUsuarioById(99);
        });

        assertTrue(exception.getMessage().contains("Usuario not found with id"));
    }

    @Test
    void testGetUsuarioById_invalidId() {
        // Arrange
        when(usuarioRepository.findById(1)).thenReturn(Optional.empty()); // ID 0, que não existe

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            usuarioService.getUsuarioById(1);
        });

        assertTrue(exception.getMessage().contains("Usuario not found with id"));
    }

    @Test
    void testDeleteUsuario_callsRepository() {
        // Arrange
        Integer usuarioId = 1;

        // Act
        usuarioService.deleteUsuario(usuarioId);

        // Assert
        verify(usuarioRepository, times(1)).deleteById(usuarioId);
    }

    @Test
    void testAddCategoriaToUsuario_callsRepository() {
        // Arrange
        List<Integer> categorias = List.of(1, 2, 3);

        // Act
        usuarioService.addCategoriaToUsuario(10, categorias);

        // Assert
        verify(usuarioRepository, times(1)).addCategoriaToUsuario(10, 1);
        verify(usuarioRepository, times(1)).addCategoriaToUsuario(10, 2);
        verify(usuarioRepository, times(1)).addCategoriaToUsuario(10, 3);
    }

    @Test
    void testAddCategoriaToUsuario_invalidCategoriaId() {
        // Arrange
        List<Integer> categorias = List.of(-1);  // ID de categoria inválido

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            usuarioService.addCategoriaToUsuario(10, categorias);
        });

        assertTrue(exception.getMessage().contains("Invalid categoria ID"));
    }

    @Test
    void testAddCategoriaToUsuario_emptyCategorias() {
        // Arrange
        List<Integer> categorias = List.of();  // Lista vazia

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            usuarioService.addCategoriaToUsuario(10, categorias);
        });

        assertTrue(exception.getMessage().contains("Categoria list cannot be empty"));
    }

    @Test
    void testGetSelosByUserId() {
        // Arrange
        List<Selo> selos = List.of(new Selo(1, "Selo1", "a"), new Selo(2, "Selo2", "a"));
        when(usuarioRepository.findSelosByUsuario(1)).thenReturn(selos);

        // Act
        List<SeloDTO> result = usuarioService.getSelosByUserId(1);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Selo1", result.get(0).getNome());
    }

    @Test
    void testGetCategoriasByUserId() {
        // Arrange
        List<CategoriaAcessibilidade> categorias = List.of(new CategoriaAcessibilidade(1, "Categoria1", "1"), new CategoriaAcessibilidade(2, "Categoria2", "2"));
        when(usuarioRepository.findCategoriasByUsuario(1)).thenReturn(categorias);

        // Act
        List<CategoriaAcessibilidadeDTO> result = usuarioService.getCategoriasByUserId(1);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Categoria1", result.get(0).getNome());
    }

    @Test
    void testUpdateCategoriasToUsuario_callsRepository() {
        // Arrange
        List<Integer> categorias = List.of(1, 2, 3);

        // Act
        usuarioService.updateCategoriasToUsuario(10, categorias);

        // Assert
        verify(usuarioRepository, times(1)).deleteCategoriasByUsuarioId(10);
        verify(usuarioRepository, times(1)).addCategoriaToUsuario(10, 1);
        verify(usuarioRepository, times(1)).addCategoriaToUsuario(10, 2);
        verify(usuarioRepository, times(1)).addCategoriaToUsuario(10, 3);
    }

    @Test
    void testUpdateCategoriasToUsuario_emptyCategories() {
        // Arrange
        List<Integer> categorias = List.of();  // Lista vazia

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            usuarioService.updateCategoriasToUsuario(10, categorias);
        });

        assertTrue(exception.getMessage().contains("Categoria list cannot be empty"));
    }

    @Test
    void testVoteOnOcorrencia_callsRepository() {
        // Arrange
        Integer usuarioId = 1;
        Integer ocorrenciaId = 100;

        // Act
        usuarioService.voteOnOcorrencia(usuarioId, ocorrenciaId);

        // Assert
        verify(usuarioRepository, times(1)).voteOnOcorrencia(usuarioId, ocorrenciaId);
    }
}
