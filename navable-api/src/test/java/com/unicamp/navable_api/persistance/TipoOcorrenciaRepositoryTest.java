package com.unicamp.navable_api.persistance;

import com.unicamp.navable_api.persistance.entities.TipoOcorrencia;
import com.unicamp.navable_api.persistance.repositories.TipoOcorrenciaRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
public class TipoOcorrenciaRepositoryTest {
    
    @Autowired
    private TipoOcorrenciaRepository tipoOcorrenciaRepository;

    private Integer idTipoOcorrenciaSalvo;
    private TipoOcorrencia tipoOcorrenciaSalvo;

    @BeforeEach
    public void setUp() {
        // Clean up any existing data
        tipoOcorrenciaRepository.deleteAll();
        
        // Create test data
        TipoOcorrencia tipoOcorrencia1 = new TipoOcorrencia(null, "Incident Type 1", "a");
        tipoOcorrenciaSalvo = tipoOcorrenciaRepository.save(tipoOcorrencia1);
        tipoOcorrenciaRepository.flush();
        idTipoOcorrenciaSalvo = tipoOcorrenciaSalvo.getIdTipoOcorrencia();
    }

    @AfterEach
    public void tearDown() {
        tipoOcorrenciaRepository.deleteAll();
    }

    @Test
    @DisplayName("Should find TipoOcorrencia by existing ID")
    public void testFindByIdTipoOcorrencia_ExistingId() {
        // When
        Optional<TipoOcorrencia> result = tipoOcorrenciaRepository.findById(idTipoOcorrenciaSalvo);

        // Then
        assertThat(result).isPresent();
        assertThat(result.get().getIdTipoOcorrencia()).isEqualTo(idTipoOcorrenciaSalvo);
        assertThat(result.get().getNome()).isEqualTo("Incident Type 1");
    }

    @Test
    @DisplayName("Should return empty when finding TipoOcorrencia by non-existing ID")
    public void testFindByIdTipoOcorrencia_NonExistingId() {
        // When
        Optional<TipoOcorrencia> result = tipoOcorrenciaRepository.findById(999);

        // Then
        assertThat(result).isNotPresent();
    }

    @Test
    @DisplayName("Should save new TipoOcorrencia successfully")
    public void testSave_NewTipoOcorrencia() {
        // Given
        TipoOcorrencia newTipoOcorrencia = new TipoOcorrencia(null, "New Incident Type", "b");

        // When
        TipoOcorrencia savedTipoOcorrencia = tipoOcorrenciaRepository.save(newTipoOcorrencia);

        // Then
        assertThat(savedTipoOcorrencia).isNotNull();
        assertThat(savedTipoOcorrencia.getIdTipoOcorrencia()).isNotNull();
        assertThat(savedTipoOcorrencia.getNome()).isEqualTo("New Incident Type");
        
        // Verify it's actually persisted
        Optional<TipoOcorrencia> foundTipoOcorrencia = tipoOcorrenciaRepository.findById(savedTipoOcorrencia.getIdTipoOcorrencia());
        assertThat(foundTipoOcorrencia).isPresent();
    }

    @Test
    @DisplayName("Should update existing TipoOcorrencia successfully")
    public void testSave_UpdateExistingTipoOcorrencia() {
        // Given
        tipoOcorrenciaSalvo.setNome("Updated Incident Type");

        // When
        TipoOcorrencia updatedTipoOcorrencia = tipoOcorrenciaRepository.save(tipoOcorrenciaSalvo);

        // Then
        assertThat(updatedTipoOcorrencia.getIdTipoOcorrencia()).isEqualTo(idTipoOcorrenciaSalvo);
        assertThat(updatedTipoOcorrencia.getNome()).isEqualTo("Updated Incident Type");
        
        // Verify the update is persisted
        Optional<TipoOcorrencia> foundTipoOcorrencia = tipoOcorrenciaRepository.findById(idTipoOcorrenciaSalvo);
        assertThat(foundTipoOcorrencia).isPresent();
        assertThat(foundTipoOcorrencia.get().getNome()).isEqualTo("Updated Incident Type");
    }

    @Test
    @DisplayName("Should find all TipoOcorrencia records")
    public void testFindAll() {
        // Given - setUp already creates one record
        TipoOcorrencia tipoOcorrencia2 = new TipoOcorrencia(null, "Incident Type 2", "b");
        TipoOcorrencia tipoOcorrencia3 = new TipoOcorrencia(null, "Incident Type 3", "c");
        tipoOcorrenciaRepository.save(tipoOcorrencia2);
        tipoOcorrenciaRepository.save(tipoOcorrencia3);

        // When
        List<TipoOcorrencia> allTiposOcorrencia = tipoOcorrenciaRepository.findAll();

        // Then
        assertThat(allTiposOcorrencia).hasSize(3);
        assertThat(allTiposOcorrencia)
            .extracting(TipoOcorrencia::getNome)
            .containsExactlyInAnyOrder("Incident Type 1", "Incident Type 2", "Incident Type 3");
    }

    @Test
    @DisplayName("Should return correct count of TipoOcorrencia records")
    public void testCount() {
        // Given - setUp already creates one record
        TipoOcorrencia tipoOcorrencia2 = new TipoOcorrencia(null, "Incident Type 2", "b");
        tipoOcorrenciaRepository.save(tipoOcorrencia2);

        // When
        long count = tipoOcorrenciaRepository.count();

        // Then
        assertThat(count).isEqualTo(2);
    }

    @Test
    @DisplayName("Should check if TipoOcorrencia exists by ID")
    public void testExistsById() {
        // When & Then - existing ID
        assertThat(tipoOcorrenciaRepository.existsById(idTipoOcorrenciaSalvo)).isTrue();
        
        // When & Then - non-existing ID
        assertThat(tipoOcorrenciaRepository.existsById(999)).isFalse();
    }

    @Test
    @DisplayName("Should delete TipoOcorrencia by ID")
    public void testDeleteById() {
        // Given
        assertThat(tipoOcorrenciaRepository.existsById(idTipoOcorrenciaSalvo)).isTrue();

        // When
        tipoOcorrenciaRepository.deleteById(idTipoOcorrenciaSalvo);

        // Then
        assertThat(tipoOcorrenciaRepository.existsById(idTipoOcorrenciaSalvo)).isFalse();
        assertThat(tipoOcorrenciaRepository.findById(idTipoOcorrenciaSalvo)).isNotPresent();
    }

    @Test
    @DisplayName("Should delete TipoOcorrencia by entity")
    public void testDelete() {
        // Given
        assertThat(tipoOcorrenciaRepository.existsById(idTipoOcorrenciaSalvo)).isTrue();

        // When
        tipoOcorrenciaRepository.delete(tipoOcorrenciaSalvo);

        // Then
        assertThat(tipoOcorrenciaRepository.existsById(idTipoOcorrenciaSalvo)).isFalse();
        assertThat(tipoOcorrenciaRepository.findById(idTipoOcorrenciaSalvo)).isNotPresent();
    }

    @Test
    @DisplayName("Should delete all TipoOcorrencia records")
    public void testDeleteAll() {
        // Given
        TipoOcorrencia tipoOcorrencia2 = new TipoOcorrencia(null, "Incident Type 2", "b");
        tipoOcorrenciaRepository.save(tipoOcorrencia2);
        assertThat(tipoOcorrenciaRepository.count()).isEqualTo(2);

        // When
        tipoOcorrenciaRepository.deleteAll();

        // Then
        assertThat(tipoOcorrenciaRepository.count()).isEqualTo(0);
        assertThat(tipoOcorrenciaRepository.findAll()).isEmpty();
    }

    @Test
    @DisplayName("Should save multiple TipoOcorrencia records")
    public void testSaveAll() {
        // Given
        TipoOcorrencia tipoOcorrencia2 = new TipoOcorrencia(null, "Incident Type 2", "b");
        TipoOcorrencia tipoOcorrencia3 = new TipoOcorrencia(null, "Incident Type 3", "c");
        List<TipoOcorrencia> tiposToSave = List.of(tipoOcorrencia2, tipoOcorrencia3);

        // When
        List<TipoOcorrencia> savedTipos = tipoOcorrenciaRepository.saveAll(tiposToSave);

        // Then
        assertThat(savedTipos).hasSize(2);
        assertThat(savedTipos).allMatch(tipo -> tipo.getIdTipoOcorrencia() != null);
        assertThat(tipoOcorrenciaRepository.count()).isEqualTo(3); // 1 from setUp + 2 new ones
    }

    @Test
    @DisplayName("Should find TipoOcorrencia records by list of IDs")
    public void testFindAllById() {
        // Given
        TipoOcorrencia tipoOcorrencia2 = new TipoOcorrencia(null, "Incident Type 2", "b");
        TipoOcorrencia savedTipo2 = tipoOcorrenciaRepository.save(tipoOcorrencia2);
        List<Integer> ids = List.of(idTipoOcorrenciaSalvo, savedTipo2.getIdTipoOcorrencia(), 999); // Include non-existing ID

        // When
        List<TipoOcorrencia> foundTipos = tipoOcorrenciaRepository.findAllById(ids);

        // Then
        assertThat(foundTipos).hasSize(2); // Only existing IDs should be returned
        assertThat(foundTipos)
            .extracting(TipoOcorrencia::getIdTipoOcorrencia)
            .containsExactlyInAnyOrder(idTipoOcorrenciaSalvo, savedTipo2.getIdTipoOcorrencia());
    }

    @Test
    @DisplayName("Should handle empty database operations gracefully")
    public void testEmptyDatabaseOperations() {
        // Given - clear all data
        tipoOcorrenciaRepository.deleteAll();

        // When & Then
        assertThat(tipoOcorrenciaRepository.findAll()).isEmpty();
        assertThat(tipoOcorrenciaRepository.count()).isEqualTo(0);
        assertThat(tipoOcorrenciaRepository.findById(1)).isNotPresent();
        assertThat(tipoOcorrenciaRepository.existsById(1)).isFalse();
    }

    /*Limits*/
    @Test
    @DisplayName("Should save TipoOcorrencia with 1-character name (boundary test)")
    public void testSave_MinLengthName() {
        TipoOcorrencia tipo = new TipoOcorrencia(null, "a", "x");
        TipoOcorrencia saved = tipoOcorrenciaRepository.save(tipo);
        assertThat(saved.getNome()).isEqualTo("a");
    }

    @Test
    @DisplayName("Should save TipoOcorrencia with long name (boundary test)")
    public void testSave_MaxLengthName() {
        String longName = "a".repeat(255); 
        TipoOcorrencia tipo = new TipoOcorrencia(null, longName, "y");
        TipoOcorrencia saved = tipoOcorrenciaRepository.save(tipo);
        assertThat(saved.getNome()).isEqualTo(longName);
    }
}