package com.unicamp.navable_api.persistance;

import com.unicamp.navable_api.persistance.entities.Ocorrencia;
import com.unicamp.navable_api.persistance.repositories.OcorrenciaRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
public class OcorrenciaRepositoryTest {

    @Autowired
    private OcorrenciaRepository ocorrenciaRepository;

    @BeforeEach
    public void setUp() {
        Ocorrencia ocorrencia1 = new Ocorrencia(1, 1, 1, -23.5505, -46.6333);
        Ocorrencia ocorrencia2 = new Ocorrencia(2, 2, 1, -23.5510, -46.6340);
        Ocorrencia ocorrencia3 = new Ocorrencia(3, 1, 2, -23.5520, -46.6350);

        ocorrenciaRepository.saveAll(Arrays.asList(ocorrencia1, ocorrencia2, ocorrencia3));
    }

    @Test
    public void testFindNearby() {
        double latitude = -23.5505;
        double longitude = -46.6333;
        List<Ocorrencia> result = ocorrenciaRepository.findNearby(latitude, longitude);
        assertThat(result).isNotEmpty();
        assertThat(result.size()).isGreaterThan(0);
    }

    @Test
    public void testFindOcorrenciaWithVoteCount() {
        Integer idOcorrencia = 1;
        List<Object[]> result = ocorrenciaRepository.findOcorrenciaWithVoteCount(idOcorrencia);
        assertThat(result).isNotEmpty();
        assertThat(result.size()).isEqualTo(1);
    }
}