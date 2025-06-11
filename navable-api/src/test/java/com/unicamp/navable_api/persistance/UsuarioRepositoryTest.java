package com.unicamp.navable_api.persistance;

import com.unicamp.navable_api.persistance.entities.*;
import com.unicamp.navable_api.persistance.repositories.UsuarioRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@SpringJUnitConfig
class UsuarioRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UsuarioRepository usuarioRepository;

    private Usuario testUsuario;
    private Selo testSelo;
    private CategoriaAcessibilidade testCategoria;
    private static int categoriaIdCounter = 1;

    @BeforeEach
    void setUp() {
        // Create required tables
        createRequiredTables();
        
        // Create test Usuario
        testUsuario = new Usuario();
        testUsuario.setEmail("test@example.com");
        testUsuario.setNome("Test User");
        testUsuario = entityManager.persistAndFlush(testUsuario);

        // Create test Selo
        testSelo = new Selo();
        testSelo.setNome("Test Selo");
        testSelo.setImagem("test-image.png");
        testSelo = entityManager.persistAndFlush(testSelo);

        // Create test CategoriaAcessibilidade
        testCategoria = new CategoriaAcessibilidade();
        testCategoria.setCategoriaAcId(categoriaIdCounter++);
        testCategoria.setNome("Test Categoria");
        testCategoria.setGrupo("Test Group");
        testCategoria = entityManager.persistAndFlush(testCategoria);

        entityManager.clear();
    }

    private void createRequiredTables() {
        EntityManager em = entityManager.getEntityManager();
        
        try {
            // Create selo_usuario table
            em.createNativeQuery(
                "CREATE TABLE IF NOT EXISTS selo_usuario (" +
                "usuario_id INTEGER NOT NULL, " +
                "selo_id INTEGER NOT NULL, " +
                "data_conquista TIMESTAMP DEFAULT CURRENT_TIMESTAMP, " +
                "PRIMARY KEY (usuario_id, selo_id))"
            ).executeUpdate();

            // Create UsuarioCategoria table
            em.createNativeQuery(
                "CREATE TABLE IF NOT EXISTS UsuarioCategoria (" +
                "id_usuario INTEGER NOT NULL, " +
                "categoria_ac_id INTEGER NOT NULL, " +
                "PRIMARY KEY (id_usuario, categoria_ac_id))"
            ).executeUpdate();

            // Create Avaliacao table
            em.createNativeQuery(
                "CREATE TABLE IF NOT EXISTS Avaliacao (" +
                "id INTEGER PRIMARY KEY AUTO_INCREMENT, " +
                "usuario_id INTEGER NOT NULL, " +
                "descricao VARCHAR(500), " +
                "rating INTEGER, " +
                "data_avaliacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP)"
            ).executeUpdate();

            // Create votos table
            em.createNativeQuery(
                "CREATE TABLE IF NOT EXISTS votos (" +
                "usuario_id INTEGER NOT NULL, " +
                "ocorrencia_id INTEGER NOT NULL, " +
                "data_voto TIMESTAMP DEFAULT CURRENT_TIMESTAMP, " +
                "PRIMARY KEY (usuario_id, ocorrencia_id))"
            ).executeUpdate();

            // Create Ocorrencia table for testing
            em.createNativeQuery(
                "CREATE TABLE IF NOT EXISTS Ocorrencia (" +
                "id INTEGER PRIMARY KEY AUTO_INCREMENT, " +
                "descricao VARCHAR(500), " +
                "data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP)"
            ).executeUpdate();

            // Insert test ocorrencia
            em.createNativeQuery(
                "INSERT INTO Ocorrencia (id, descricao) VALUES (1, 'Test Ocorrencia') " +
                "ON DUPLICATE KEY UPDATE descricao = 'Test Ocorrencia'"
            ).executeUpdate();

            entityManager.flush();
        } catch (Exception e) {
            // Tables might already exist, ignore
            System.out.println("Note: Some tables might already exist: " + e.getMessage());
        }
    }

    @Test
    void testFindByEmail_WhenEmailExists_ShouldReturnUsuario() {
        // When
        Optional<Usuario> result = usuarioRepository.findByEmail("test@example.com");

        // Then
        assertThat(result).isPresent();
        assertThat(result.get().getEmail()).isEqualTo("test@example.com");
        assertThat(result.get().getNome()).isEqualTo("Test User");
    }

    @Test
    void testFindByEmail_WhenEmailDoesNotExist_ShouldReturnEmpty() {
        // When
        Optional<Usuario> result = usuarioRepository.findByEmail("nonexistent@example.com");

        // Then
        assertThat(result).isEmpty();
    }

    @Test
    @Transactional
    void testFindCategoriasByUsuario_WhenUserHasCategorias_ShouldReturnCategorias() {
        // Given
        usuarioRepository.addCategoriaToUsuario(testUsuario.getIdUsuario(), testCategoria.getCategoriaAcId());
        entityManager.flush();

        // When
        List<CategoriaAcessibilidade> result = usuarioRepository.findCategoriasByUsuario(testUsuario.getIdUsuario());

        // Then
        assertThat(result).isNotEmpty();
        assertThat(result.get(0).getNome()).isEqualTo("Test Categoria");
        assertThat(result.get(0).getGrupo()).isEqualTo("Test Group");
    }

    @Test
    @Transactional
    void testDeleteById_ShouldRemoveUsuario() {
        // Given
        Integer usuarioId = testUsuario.getIdUsuario();
        
        // Verify usuario exists
        Optional<Usuario> beforeDelete = usuarioRepository.findById(usuarioId);
        assertThat(beforeDelete).isPresent();

        // When
        usuarioRepository.deleteById(usuarioId);
        entityManager.flush();

        // Then
        Optional<Usuario> afterDelete = usuarioRepository.findById(usuarioId);
        assertThat(afterDelete).isEmpty();
    }

    @Test
    void testFindSelosByUsuario_WhenUserHasNoSelos_ShouldReturnEmptyList() {
        // When
        List<Selo> result = usuarioRepository.findSelosByUsuario(testUsuario.getIdUsuario());

        // Then
        assertThat(result).isEmpty();
    }


    // Edge cases and error scenarios
    @Test
    void testFindByEmail_WithNullEmail_ShouldReturnEmpty() {
        // When
        Optional<Usuario> result = usuarioRepository.findByEmail(null);

        // Then
        assertThat(result).isEmpty();
    }

    @Test
    void testFindByEmail_WithEmptyEmail_ShouldReturnEmpty() {
        // When
        Optional<Usuario> result = usuarioRepository.findByEmail("");

        // Then
        assertThat(result).isEmpty();
    }

    @Test
    @Transactional
    void testAddSeloToUsuario_WithNonExistentUsuario_ShouldHandleGracefully() {
        Integer nonExistentUsuarioId = 99999;
        
        // When/Then - This should throw an exception based on DB constraints
        assertThrows(Exception.class, () -> {
            usuarioRepository.addSeloToUsuario(nonExistentUsuarioId, testSelo.getIdSelo());
            entityManager.flush();
        });
    }

    // Helper method to create additional categoria if needed
    private CategoriaAcessibilidade createTestCategoria(String nome, String grupo) {
        CategoriaAcessibilidade categoria = new CategoriaAcessibilidade();
        categoria.setCategoriaAcId(categoriaIdCounter++);
        categoria.setNome(nome);
        categoria.setGrupo(grupo);
        return entityManager.persistAndFlush(categoria);
    }

    @Test
    void testSaveUsuario_WithEmailLength255_ShouldPersistCorrectly() {
        String longEmail = "a".repeat(243) + "@test.com"; // total 255

        Usuario usuario = new Usuario();
        usuario.setEmail(longEmail);
        usuario.setNome("User Max");
        usuario.setSenha("123");
        usuario.setPontos(0);

        Usuario saved = entityManager.persistAndFlush(usuario);

        assertThat(saved.getEmail()).isEqualTo(longEmail);
    }

    @Test
    void testSaveUsuario_WithOneCharacterName_ShouldPersistCorrectly() {
        // Given
        Usuario usuario = new Usuario();
        usuario.setEmail("shortname@example.com");
        usuario.setNome("A");

        // When
        Usuario saved = entityManager.persistAndFlush(usuario);

        // Then
        assertThat(saved.getNome()).isEqualTo("A");
    }

    @Test
    void testSaveUsuario_WithEmptyEmail_ShouldPersist() {
        Usuario usuario = new Usuario();
        usuario.setEmail("");
        usuario.setNome("Empty Email");
        usuario.setSenha("123");
        usuario.setPontos(0);

        Usuario saved = entityManager.persistAndFlush(usuario);

        assertThat(saved.getEmail()).isEmpty();
    }

}