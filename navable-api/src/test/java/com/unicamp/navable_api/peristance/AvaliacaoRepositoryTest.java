package com.unicamp.navable_api.peristance;

import com.unicamp.navable_api.persistance.entities.Avaliacao;
import com.unicamp.navable_api.persistance.repositories.AvaliacaoRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
public class AvaliacaoRepositoryTest {

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    @BeforeEach
    public void setUp() {
        Avaliacao avaliacao1 = new Avaliacao(1, 1, 1, "Great service", 5, LocalDate.now());
        Avaliacao avaliacao2 = new Avaliacao(2, 1, 1, "Average experience", 3, LocalDate.now());
        Avaliacao avaliacao3 = new Avaliacao(3, 2, 1, "Not good", 1, LocalDate.now());

        avaliacaoRepository.saveAll(List.of(avaliacao1, avaliacao2, avaliacao3));
    }

    @Test
    public void testFindByEstabelecimentoId() {
        Integer estId = 1;
        List<Avaliacao> result = avaliacaoRepository.findByEstabelecimentoId(estId);
        assertThat(result).isNotEmpty();
        assertThat(result.size()).isEqualTo(3);
    }

    @Test
    public void testFindByEstabelecimentoIdAndFilters() {
        Integer estId = 1;
        Integer nota = 5;
        LocalDate dataInicial = LocalDate.now().minusDays(10);
        LocalDate dataFinal = LocalDate.now();

        List<Avaliacao> result = avaliacaoRepository.findByEstabelecimentoIdAndFilters(estId, nota, dataInicial, dataFinal);
        assertThat(result).isNotEmpty();
        assertThat(result.size()).isEqualTo(1);
        assertThat(result.get(0).getNota()).isEqualTo(5);
    }
}