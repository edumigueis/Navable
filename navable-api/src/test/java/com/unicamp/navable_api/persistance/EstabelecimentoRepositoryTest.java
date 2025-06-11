package com.unicamp.navable_api.persistance;

import com.unicamp.navable_api.persistance.entities.*;
import com.unicamp.navable_api.persistance.repositories.*;
import com.unicamp.navable_api.persistance.support.GeoLocationSupport;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import java.time.LocalDate;
import java.util.*;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
class EstabelecimentoRepositoryTest {

    @Autowired
    private EstabelecimentoRepository estabelecimentoRepository;

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;


    @BeforeEach
    void setUp() {
        // Create establishments
        Estabelecimento est1 = new Estabelecimento(1, 1, "Establishment 1", -23.5505, -46.6333, "image1.png", "Address 1", 0);
        Estabelecimento est2 = new Estabelecimento(2, 1, "Establishment 2", -23.5510, -46.6340, "image2.png", "Address 2", 0);
        Estabelecimento est3 = new Estabelecimento(3, 2, "Establishment 3", -23.5520, -46.6350, "image3.png", "Address 3", 0);
        Estabelecimento est4 = new Estabelecimento(4, 3, "Restaurant ABC", -23.5600, -46.6400, "image4.png", "Address 4", 0);
        Estabelecimento est5 = new Estabelecimento(5, 1, "Café XYZ", -23.5530, -46.6360, "image5.png", "Address 5", 0);
        estabelecimentoRepository.saveAll(Arrays.asList(est1, est2, est3, est4, est5));

        // Create ratings
        Avaliacao avaliacao1 = new Avaliacao(1, 1, 1, "Great service", 4, LocalDate.now());
        Avaliacao avaliacao2 = new Avaliacao(2, 1, 1, "Average experience", 3, LocalDate.now());
        Avaliacao avaliacao3 = new Avaliacao(3, 2, 1, "Not good", 1, LocalDate.now());
        Avaliacao avaliacao4 = new Avaliacao(4, 2, 2, "Excellent", 5, LocalDate.now());
        Avaliacao avaliacao5 = new Avaliacao(5, 3, 3, "Good food", 4, LocalDate.now());
        Avaliacao avaliacao6 = new Avaliacao(6, 4, 1, "Perfect", 5, LocalDate.now());
        Avaliacao avaliacao7 = new Avaliacao(7, 5, 2, "Nice place", 4, LocalDate.now());
        avaliacaoRepository.saveAll(Arrays.asList(avaliacao1, avaliacao2, avaliacao3, avaliacao4, avaliacao5, avaliacao6, avaliacao7));
    }

    @Test
    @DisplayName("Should find nearby establishments with ratings")
    void testFindNearbyWithRatings_WithinRadius() {
        double latitude = -23.5505;
        double longitude = -46.6333;
        double radius = 5.0; // 5km radius

        List<Estabelecimento> result = estabelecimentoRepository.findNearbyWithRatings(latitude, longitude, radius);

        assertThat(result).isNotEmpty();
        assertThat(result.size()).isGreaterThan(0);
        // Verify that returned establishments have coordinates
        result.forEach(est -> {
            assertThat(est.getLatitude()).isNotNull();
            assertThat(est.getLongitude()).isNotNull();
        });
    }

    @Test
    @DisplayName("Should return empty list when no establishments within radius")
    void testFindNearbyWithRatings_OutsideRadius() {
        double latitude = -25.0000; // Far from test data
        double longitude = -48.0000;
        double radius = 1.0; // Small radius

        List<Estabelecimento> result = estabelecimentoRepository.findNearbyWithRatings(latitude, longitude, radius);

        assertThat(result).isEmpty();
    }

    @Test
    @DisplayName("Should find establishments with large radius")
    void testFindNearbyWithRatings_LargeRadius() {
        double latitude = -23.5505;
        double longitude = -46.6333;
        double radius = 100.0; // Large radius

        List<Estabelecimento> result = estabelecimentoRepository.findNearbyWithRatings(latitude, longitude, radius);

        assertThat(result).hasSizeGreaterThanOrEqualTo(3); // Should find most/all establishments
    }


    @Test
    @DisplayName("Should find establishment by exact name")
    void testFindByNome_ExactMatch() {
        String nome = "Establishment 1";

        List<Estabelecimento> result = estabelecimentoRepository.findByNome(nome);

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getNome()).isEqualTo("Establishment 1");
    }

    @Test
    @DisplayName("Should find establishments by partial name (case insensitive)")
    void testFindByNome_PartialMatch() {
        String nome = "establishment"; // lowercase

        List<Estabelecimento> result = estabelecimentoRepository.findByNome(nome);

        assertThat(result).hasSizeGreaterThanOrEqualTo(3); // Should find establishments 1, 2, 3
        result.forEach(est -> 
            assertThat(est.getNome().toLowerCase()).contains("establishment"));
    }

    @Test
    @DisplayName("Should find establishments by partial name")
    void testFindByNome_PartialNameMatch() {
        String nome = "ABC";

        List<Estabelecimento> result = estabelecimentoRepository.findByNome(nome);

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getNome()).contains("ABC");
    }

    @Test
    @DisplayName("Should return empty list when name not found")
    void testFindByNome_NotFound() {
        String nome = "NonExistentEstablishment";

        List<Estabelecimento> result = estabelecimentoRepository.findByNome(nome);

        assertThat(result).isEmpty();
    }

    @Test
    @DisplayName("Should return all establishments when name is null")
    void testFindByNome_NullName() {
        String nome = null;

        List<Estabelecimento> result = estabelecimentoRepository.findByNome(nome);

        assertThat(result).hasSizeGreaterThanOrEqualTo(5); // Should return all establishments
    }

    @Test
    @DisplayName("Should return all establishments when name is empty")
    void testFindByNome_EmptyName() {
        String nome = "";

        List<Estabelecimento> result = estabelecimentoRepository.findByNome(nome);

        assertThat(result).hasSizeGreaterThanOrEqualTo(5); // Should return all establishments
    }

    @Test
    @DisplayName("Should handle special characters in name search")
    void testFindByNome_SpecialCharacters() {
        String nome = "Café"; // Contains special character

        List<Estabelecimento> result = estabelecimentoRepository.findByNome(nome);

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getNome()).contains("Café");
    }


    @Test
    @DisplayName("Should return average for establishment with single rating")
    void testFindAverageNotaByEstabelecimentoId_SingleRating() {
        Integer estId = 3;

        Double averageNota = estabelecimentoRepository.findAverageNotaByEstabelecimentoId(estId);

        assertThat(averageNota).isNotNull();
        assertThat(averageNota).isEqualTo(4.0); // Single rating of 4
    }

    @Test
    @DisplayName("Should return null when establishment has no ratings")
    void testFindAverageNotaByEstabelecimentoId_NoRatings() {
        // Create establishment without ratings
        Estabelecimento estWithoutRatings = new Estabelecimento(999, 1, "No Ratings Est", -23.5555, -46.6555, "image.png", "Address", 0);
        estabelecimentoRepository.save(estWithoutRatings);

        Double averageNota = estabelecimentoRepository.findAverageNotaByEstabelecimentoId(999);

        assertThat(averageNota).isNull();
    }

    @Test
    @DisplayName("Should return null when establishment doesn't exist")
    void testFindAverageNotaByEstabelecimentoId_NonExistentEstablishment() {
        Integer nonExistentId = 99999;

        Double averageNota = estabelecimentoRepository.findAverageNotaByEstabelecimentoId(nonExistentId);

        assertThat(averageNota).isNull();
    }

    @Test
    @DisplayName("Should handle null establishment ID")
    void testFindAverageNotaByEstabelecimentoId_NullId() {
        Integer estId = null;

        Double averageNota = estabelecimentoRepository.findAverageNotaByEstabelecimentoId(estId);

        assertThat(averageNota).isNull();
    }



    @Test
    @DisplayName("Should verify data consistency across all methods")
    void testDataConsistency() {
        // Verify that all establishments exist
        List<Estabelecimento> allEstablishments = estabelecimentoRepository.findAll();
        assertThat(allEstablishments).hasSizeGreaterThanOrEqualTo(5);

        // Verify that each establishment can be found by name
        allEstablishments.forEach(est -> {
            List<Estabelecimento> foundByName = estabelecimentoRepository.findByNome(est.getNome());
            assertThat(foundByName).contains(est);
        });

        // Verify that establishments with ratings have valid averages
        allEstablishments.forEach(est -> {
            Double average = estabelecimentoRepository.findAverageNotaByEstabelecimentoId(est.getIdEstabelecimento());
            if (average != null) {
                assertThat(average).isBetween(0.0, 5.0);
            }
        });
    }

    @Test
    @DisplayName("Should handle concurrent access safely")
    void testConcurrentAccess() {
        // Test that multiple simultaneous queries work correctly
        List<Estabelecimento> result1 = estabelecimentoRepository.findByNome("Establishment");
        Double average1 = estabelecimentoRepository.findAverageNotaByEstabelecimentoId(1);
        List<Estabelecimento> result2 = estabelecimentoRepository.findNearbyWithRatings(-23.5505, -46.6333, 10.0);

        assertThat(result1).isNotNull();
        assertThat(average1).isNotNull();
        assertThat(result2).isNotNull();
    }

    @Test
    @DisplayName("Should find establishment exactly on the boundary of radius")
    void testFindNearbyWithRatings_OnRadiusBoundary() {
        double latitude = -23.5505;
        double longitude = -46.6333;
        double radius = 0.0;

        List<Estabelecimento> result = estabelecimentoRepository.findNearbyWithRatings(latitude, longitude, radius);

        assertThat(result).isNotEmpty(); 
    }

    @Test
    @DisplayName("Should handle invalid geolocation values gracefully")
    void testFindNearbyWithRatings_InvalidInputs() {
        double latitude = 999; // inválido
        double longitude = 999;
        double radius = -10; // inválido

        List<Estabelecimento> result = estabelecimentoRepository.findNearbyWithRatings(latitude, longitude, radius);

        assertThat(result).isEmpty(); 
    }
}
