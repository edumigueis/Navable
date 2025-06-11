package com.unicamp.navable_api.persistance;

import com.unicamp.navable_api.persistance.entities.CategoriaAcessibilidade;
import com.unicamp.navable_api.persistance.repositories.CategoriaAcessibilidadeRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
@DisplayName("CategoriaAcessibilidade Repository Tests")
class CategoriaAcessibilidadeRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CategoriaAcessibilidadeRepository repository;

    private CategoriaAcessibilidade categoria1;
    private CategoriaAcessibilidade categoria2;


    @BeforeEach
    void setUp() {
        // Clear any existing data
        repository.deleteAll();
        entityManager.flush();
        entityManager.clear();

        // Create test data
        categoria1 = createCategoria(1, "Mobilidade Reduzida", "Físico");
        categoria2 = createCategoria(2, "Deficiência Visual", "Sensorial");
    }

    private CategoriaAcessibilidade createCategoria(Integer id, String nome, String grupo) {
        CategoriaAcessibilidade categoria = new CategoriaAcessibilidade();
        categoria.setCategoriaAcId(id);
        categoria.setNome(nome);
        categoria.setGrupo(grupo);
        return categoria;
    }

    @Test
    void shouldSaveAndRetrieveCategoria() {
        // Given
        CategoriaAcessibilidade savedCategoria = repository.save(categoria1);

        // When
        Optional<CategoriaAcessibilidade> found = repository.findById(savedCategoria.getCategoriaAcId());

        // Then
        assertThat(found).isPresent();
        assertThat(found.get().getNome()).isEqualTo("Mobilidade Reduzida");
        assertThat(found.get().getGrupo()).isEqualTo("Físico");
        assertThat(found.get().getCategoriaAcId()).isEqualTo(1);
    }

    @Test
    void shouldFindAllCategorias() {
        // Given
        repository.save(categoria1);
        repository.save(categoria2);
        entityManager.flush();

        // When
        List<CategoriaAcessibilidade> categorias = repository.findAll();

        // Then
        assertThat(categorias).hasSize(2);
        assertThat(categorias)
                .extracting(CategoriaAcessibilidade::getNome)
                .containsExactlyInAnyOrder("Mobilidade Reduzida", "Deficiência Visual");
    }

    @Test
    void shouldReturnEmptyWhenCategoriaNotFound() {
        // When
        Optional<CategoriaAcessibilidade> found = repository.findById(999);

        // Then
        assertThat(found).isEmpty();
    }

    @Test
    void shouldUpdateCategoria() {
        // Given
        CategoriaAcessibilidade saved = repository.save(categoria1);
        entityManager.flush();
        entityManager.clear();

        // When
        saved.setNome("Mobilidade Reduzida Atualizada");
        saved.setGrupo("Físico Atualizado");
        CategoriaAcessibilidade updated = repository.save(saved);
        entityManager.flush();

        // Then
        Optional<CategoriaAcessibilidade> found = repository.findById(updated.getCategoriaAcId());
        assertThat(found).isPresent();
        assertThat(found.get().getNome()).isEqualTo("Mobilidade Reduzida Atualizada");
        assertThat(found.get().getGrupo()).isEqualTo("Físico Atualizado");
    }

    @Test
    void shouldDeleteCategoria() {
        // Given
        CategoriaAcessibilidade saved = repository.save(categoria1);
        entityManager.flush();
        Integer categoriaId = saved.getCategoriaAcId();

        // When
        repository.deleteById(categoriaId);
        entityManager.flush();

        // Then
        Optional<CategoriaAcessibilidade> found = repository.findById(categoriaId);
        assertThat(found).isEmpty();
    }

    @Test
    void shouldDeleteAllCategorias() {
        // Given
        repository.save(categoria1);
        repository.save(categoria2);
        entityManager.flush();

        // When
        repository.deleteAll();
        entityManager.flush();

        // Then
        List<CategoriaAcessibilidade> categorias = repository.findAll();
        assertThat(categorias).isEmpty();
    }

    @Test
    void shouldCountCategorias() {
        // Given
        repository.save(categoria1);
        repository.save(categoria2);
        entityManager.flush();

        // When
        long count = repository.count();

        // Then
        assertThat(count).isEqualTo(2);
    }

    @Test
    void shouldHandleNullValues() {
        // Given
        CategoriaAcessibilidade categoriaWithNulls = createCategoria(3, null, null);

        // When
        CategoriaAcessibilidade saved = repository.save(categoriaWithNulls);
        entityManager.flush();

        // Then
        Optional<CategoriaAcessibilidade> found = repository.findById(saved.getCategoriaAcId());
        assertThat(found).isPresent();
        assertThat(found.get().getNome()).isNull();
        assertThat(found.get().getGrupo()).isNull();
    }

    @Test
    void shouldSaveMultipleCategorias() {
        // Given
        List<CategoriaAcessibilidade> categorias = List.of(categoria1, categoria2);

        // When
        List<CategoriaAcessibilidade> savedCategorias = repository.saveAll(categorias);
        entityManager.flush();

        // Then
        assertThat(savedCategorias).hasSize(2);
        assertThat(repository.count()).isEqualTo(2);
    }
}