package com.unicamp.navable_api.persistance;

import com.unicamp.navable_api.persistance.entities.Avaliacao;
import com.unicamp.navable_api.persistance.repositories.AvaliacaoRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import java.time.LocalDate;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
public class AvaliacaoRepositoryTest {

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    @BeforeEach
    public void setUp() {
        // Clear any existing data
        avaliacaoRepository.deleteAll();
        
        // Create test data with different establishments, users, ratings, and dates
        // Establishment 1 has 3 reviews: two 5-star and one 3-star
        Avaliacao avaliacao1 = new Avaliacao(1, 1, 1, "Great service", 5, LocalDate.now().minusDays(1));
        Avaliacao avaliacao2 = new Avaliacao(2, 2, 1, "Average experience", 3, LocalDate.now().minusDays(2));
        Avaliacao avaliacao3 = new Avaliacao(3, 3, 1, "Excellent!", 5, LocalDate.now().minusDays(5));
        
        // Establishment 2 has 1 review
        Avaliacao avaliacao4 = new Avaliacao(4, 1, 2, "Not good", 1, LocalDate.now().minusDays(3));
        
        // Establishment 3 has 1 review
        Avaliacao avaliacao5 = new Avaliacao(5, 2, 3, "OK service", 3, LocalDate.now().minusDays(7));
        
        avaliacaoRepository.saveAll(List.of(avaliacao1, avaliacao2, avaliacao3, avaliacao4, avaliacao5));
    }

    @Test
    public void testFindByEstabelecimentoId_WhenEstabelecimentoExists() {
        Integer estId = 1;
        List<Avaliacao> result = avaliacaoRepository.findByEstabelecimentoId(estId);
        
        assertThat(result).isNotEmpty();
        assertThat(result).hasSize(3);
        assertThat(result).allMatch(a -> a.getIdEstabelecimento().equals(estId));
    }

    @Test
    public void testFindByEstabelecimentoId_WhenEstabelecimentoDoesNotExist() {
        Integer estId = 999;
        List<Avaliacao> result = avaliacaoRepository.findByEstabelecimentoId(estId);
        
        assertThat(result).isEmpty();
    }

    @Test
    public void testFindByEstabelecimentoIdAndFilters_WithAllFilters() {
        Integer estId = 1;
        Integer nota = 5;
        LocalDate dataInicial = LocalDate.now().minusDays(10);
        LocalDate dataFinal = LocalDate.now();
        
        List<Avaliacao> result = avaliacaoRepository.findByEstabelecimentoIdAndFilters(
            estId, nota, dataInicial, dataFinal);
        
        assertThat(result).isNotEmpty();
        assertThat(result).hasSize(2);
        assertThat(result).allMatch(a -> a.getNota().equals(5) && a.getIdEstabelecimento().equals(estId));
    }

    @Test
    public void testFindByEstabelecimentoIdAndFilters_WithNullFilters() {
        Integer estId = 1;
        
        List<Avaliacao> result = avaliacaoRepository.findByEstabelecimentoIdAndFilters(
            estId, null, null, null);
        
        assertThat(result).hasSize(3);
        assertThat(result).allMatch(a -> a.getIdEstabelecimento().equals(estId));
    }

    @Test
    public void testFindByEstabelecimentoIdAndFilters_WithNotaFilter() {
        Integer estId = 1;
        Integer nota = 3;
        
        List<Avaliacao> result = avaliacaoRepository.findByEstabelecimentoIdAndFilters(
            estId, nota, null, null);
        
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getNota()).isEqualTo(3);
        assertThat(result.get(0).getIdEstabelecimento()).isEqualTo(estId);
    }

    @Test
    public void testFindByEstabelecimentoIdAndFilters_WithDateRangeFilter() {
        Integer estId = 1;
        LocalDate dataInicial = LocalDate.now().minusDays(3);
        LocalDate dataFinal = LocalDate.now();
        
        List<Avaliacao> result = avaliacaoRepository.findByEstabelecimentoIdAndFilters(
            estId, null, dataInicial, dataFinal);
        
        assertThat(result).hasSize(2); // Should exclude the one from 5 days ago
        assertThat(result).allMatch(a -> 
            !a.getTimestamp().isBefore(dataInicial) && !a.getTimestamp().isAfter(dataFinal));
    }

    @Test
    public void testFindByUsuarioId_WhenUsuarioExists() {
        Integer userId = 1;
        List<Avaliacao> result = avaliacaoRepository.findByUsuarioId(userId);
        
        assertThat(result).isNotEmpty();
        assertThat(result).hasSize(2); // User 1 has 2 reviews (establishments 1 and 2)
        assertThat(result).allMatch(a -> a.getIdUsuario().equals(userId));
    }

    @Test
    public void testFindByUsuarioId_WhenUsuarioDoesNotExist() {
        Integer userId = 999;
        List<Avaliacao> result = avaliacaoRepository.findByUsuarioId(userId);
        
        assertThat(result).isEmpty();
    }

    @Test
     void testFindByEstabelecimentoIdAndFilters_WithSpecificDateRange() {
        Integer estId = 1;
        LocalDate dataInicial = LocalDate.now().minusDays(2);
        LocalDate dataFinal = LocalDate.now().minusDays(1);

        List<Avaliacao> result = avaliacaoRepository.findByEstabelecimentoIdAndFilters(
                estId, null, dataInicial, dataFinal);

        assertThat(result).hasSize(2);
    }

    @Test
     void testFindByEstabelecimentoIdAndFilters_CombinedFilters() {
        Integer estId = 1;
        Integer nota = 5;
        LocalDate dataInicial = LocalDate.now().minusDays(2);
        LocalDate dataFinal = LocalDate.now();

        List<Avaliacao> result = avaliacaoRepository.findByEstabelecimentoIdAndFilters(
                estId, nota, dataInicial, dataFinal);

        assertThat(result).hasSize(1);
    }

    @Test
     void testDateBoundaryConditions() {
        Integer estId = 1;
        LocalDate exactDate = LocalDate.now().minusDays(1);

        List<Avaliacao> result = avaliacaoRepository.findByEstabelecimentoIdAndFilters(
                estId, null, exactDate, exactDate);

        assertThat(result).hasSize(1);
    }

    @Test
     void testFindByEstabelecimentoIdAndFilters_NoResults() {
        Integer estId = 1;
        Integer nota = 2;

        List<Avaliacao> result = avaliacaoRepository.findByEstabelecimentoIdAndFilters(
                estId, nota, null, null);

        assertThat(result).isEmpty();
    }

    @Test
    void testFindByUsuarioIdAndFilters_WithAllFilters() {
        Integer userId = 1;
        Integer nota = 5;
        LocalDate dataInicial = LocalDate.now().minusDays(10);
        LocalDate dataFinal = LocalDate.now();

        List<Avaliacao> result = avaliacaoRepository.findByUsuarioIdAndFilters(
                userId, nota, dataInicial, dataFinal);

        assertThat(result).isNotEmpty();
        assertThat(result).hasSize(1);
        assertThat(result).allMatch(a -> a.getIdUsuario().equals(userId) && a.getNota().equals(nota));
    }

    @Test
    void testFindByUsuarioIdAndFilters_WithNullFilters() {
        Integer userId = 1;

        List<Avaliacao> result = avaliacaoRepository.findByUsuarioIdAndFilters(
                userId, null, null, null);

        assertThat(result).hasSize(2);
        assertThat(result).allMatch(a -> a.getIdUsuario().equals(userId));
    }

    @Test
    void testFindByUsuarioIdAndFilters_WithNotaFilter() {
        Integer userId = 1;
        Integer nota = 1;

        List<Avaliacao> result = avaliacaoRepository.findByUsuarioIdAndFilters(
                userId, nota, null, null);

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getNota()).isEqualTo(1);
        assertThat(result.get(0).getIdUsuario()).isEqualTo(userId);
    }

    @Test
    void testFindByUsuarioIdAndFilters_NoResultsForFilters() {
        Integer userId = 1;
        Integer nota = 2;

        List<Avaliacao> result = avaliacaoRepository.findByUsuarioIdAndFilters(
                userId, nota, null, null);

        assertThat(result).isEmpty();
    }

    @Test
    void testFindByUsuarioIdAndFilters_DateRangeInverted() {
        Integer userId = 1;
        LocalDate dataInicial = LocalDate.now();
        LocalDate dataFinal = LocalDate.now().minusDays(5);

        List<Avaliacao> result = avaliacaoRepository.findByUsuarioIdAndFilters(
                userId, null, dataInicial, dataFinal);

        assertThat(result).isEmpty();
    }

    @Test
    void testFindByUsuarioIdAndFilters_WhenUsuarioDoesNotExist() {
        Integer userId = 999;
        List<Avaliacao> result = avaliacaoRepository.findByUsuarioIdAndFilters(
                userId, null, null, null);

        assertThat(result).isEmpty();
    }

    @Test
    void testFindAverageAvaliacaoByEstabelecimento_WhenNoReviewsExist() {
        avaliacaoRepository.deleteAll();

        List<Object[]> result = avaliacaoRepository.findAverageAvaliacaoByEstabelecimento();

        assertThat(result).isEmpty();
    }



    @AfterEach
    public void tearDown() {
        avaliacaoRepository.deleteAll();
    }
}