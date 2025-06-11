package com.unicamp.navable_api.persistance;

import com.unicamp.navable_api.persistance.entities.Ocorrencia;
import com.unicamp.navable_api.persistance.entities.Votos;
import com.unicamp.navable_api.persistance.repositories.OcorrenciaRepository;
import com.unicamp.navable_api.persistance.support.GeoLocationSupport;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
public class OcorrenciaRepositoryTest {

    @Autowired
    private OcorrenciaRepository ocorrenciaRepository;


    private Ocorrencia ocorrenciaSaoPaulo;
    private Ocorrencia ocorrenciaNearby;
    private Ocorrencia ocorrenciaFarAway;

    @BeforeEach
    public void setUp() {
        // São Paulo city center coordinates
        ocorrenciaSaoPaulo = new Ocorrencia(1, 1, 1, -23.5505, -46.6333);
        
        // Very close to São Paulo (about 100m away)
        ocorrenciaNearby = new Ocorrencia(2, 2, 1, -23.5510, -46.6340);
        
        // Far from São Paulo (Rio de Janeiro coordinates - about 400km away)
        ocorrenciaFarAway = new Ocorrencia(3, 1, 2, -22.9068, -43.1729);

        ocorrenciaRepository.saveAll(Arrays.asList(
            ocorrenciaSaoPaulo, 
            ocorrenciaNearby, 
            ocorrenciaFarAway
        ));
    }

    @Test
    @DisplayName("Should find all ocorrencias with large distance")
    public void testFindNearbyWithLargeDistance() {
        double latitude = -23.5505;
        double longitude = -46.6333;
        double distance = 500.0; // 500km radius

        List<Ocorrencia> result = ocorrenciaRepository.findNearby(latitude, longitude, distance);

        assertThat(result).hasSize(3); // Should find all three
    }

    @Test
    @DisplayName("Should return empty list when no ocorrencias are within distance")
    public void testFindNearbyNoResults() {
        double latitude = 0.0; // Equator
        double longitude = 0.0; // Prime Meridian
        double distance = 1.0; // 1km radius

        List<Ocorrencia> result = ocorrenciaRepository.findNearby(latitude, longitude, distance);

        assertThat(result).isEmpty();
    }

    // Tests for findNearby with default distance
    @Test
    @DisplayName("Should use default distance when not specified")
    public void testFindNearbyWithDefaultDistance() {
        double latitude = -23.5505;
        double longitude = -46.6333;

        List<Ocorrencia> result = ocorrenciaRepository.findNearby(latitude, longitude);

        assertThat(result).isNotEmpty();
        // The result should depend on GeoLocationSupport.DEFAULT_SEARCH_RADIUS_KM
        // Assuming default is reasonable (like 5-10km), should find at least the nearby ones
        assertThat(result.size()).isGreaterThanOrEqualTo(2);
    }

    @Test
    @DisplayName("Should handle edge coordinates correctly")
    public void testFindNearbyEdgeCoordinates() {
        // Test with extreme coordinates
        List<Ocorrencia> result1 = ocorrenciaRepository.findNearby(90.0, 180.0, 1000.0); // North Pole area
        List<Ocorrencia> result2 = ocorrenciaRepository.findNearby(-90.0, -180.0, 1000.0); // South Pole area

        assertThat(result1).isEmpty();
        assertThat(result2).isEmpty();
    }


    @Test
    @DisplayName("Should return empty list for non-existent ocorrencia")
    public void testFindOcorrenciaWithVoteCountNonExistent() {
        List<Object[]> result = ocorrenciaRepository.findOcorrenciaWithVoteCount(999);

        assertThat(result).isEmpty();
    }


    // Integration tests
    @Test
    @DisplayName("Should work with repository inheritance methods")
    public void testJpaRepositoryMethods() {
        // Test that basic JPA methods still work
        Optional<Ocorrencia> found = ocorrenciaRepository.findById(1);
        assertThat(found).isPresent();
        assertThat(found.get().getIdOcorrencia()).isEqualTo(1);

        long count = ocorrenciaRepository.count();
        assertThat(count).isEqualTo(3);

        List<Ocorrencia> all = ocorrenciaRepository.findAll();
        assertThat(all).hasSize(3);
    }

    @Test
    @DisplayName("Should handle null parameters gracefully")
    public void testNullParameterHandling() {
        // Test null handling in findOcorrenciaWithVoteCount
        List<Object[]> result = ocorrenciaRepository.findOcorrenciaWithVoteCount(null);
        assertThat(result).isEmpty();
    }

    // Performance test (optional)
    @Test
    @DisplayName("Should perform geolocation search efficiently with many records")
    public void testFindNearbyPerformance() {
        // Create many ocorrencias for performance testing
        List<Ocorrencia> manyOcorrencias = new ArrayList<>();
        for (int i = 10; i < 110; i++) {
            // Spread around São Paulo area
            double lat = -23.5505 + (Math.random() - 0.5) * 0.1;
            double lon = -46.6333 + (Math.random() - 0.5) * 0.1;
            manyOcorrencias.add(new Ocorrencia(i, i, 1, lat, lon));
        }
        ocorrenciaRepository.saveAll(manyOcorrencias);

        long startTime = System.currentTimeMillis();
        List<Ocorrencia> result = ocorrenciaRepository.findNearby(-23.5505, -46.6333, 10.0);
        long endTime = System.currentTimeMillis();

        assertThat(result).isNotEmpty();
        assertThat(endTime - startTime).isLessThan(1000); // Should complete in less than 1 second
    }

    @Test
    @DisplayName("Should handle invalid coordinates gracefully")
    public void testFindNearbyWithInvalidCoordinates() {
        double invalidLatitude = -100.0;  // latitud inválida (< -90)
        double invalidLongitude = 200.0;  // longitud inválida (> 180)

        List<Ocorrencia> result = ocorrenciaRepository.findNearby(invalidLatitude, invalidLongitude, 10.0);

        assertThat(result).isEmpty();
    }

}