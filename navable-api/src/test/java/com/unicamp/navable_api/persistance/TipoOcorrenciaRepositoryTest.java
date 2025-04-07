package com.unicamp.navable_api.persistance;

import com.unicamp.navable_api.persistance.entities.TipoOcorrencia;
import com.unicamp.navable_api.persistance.repositories.TipoOcorrenciaRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
public class TipoOcorrenciaRepositoryTest {

    @Autowired
    private TipoOcorrenciaRepository tipoOcorrenciaRepository;

    @BeforeEach
    public void setUp() {
        TipoOcorrencia tipoOcorrencia1 = new TipoOcorrencia(1, "Incident Type 1", "a");
        TipoOcorrencia tipoOcorrencia2 = new TipoOcorrencia(2, "Incident Type 2", "b");

        tipoOcorrenciaRepository.save(tipoOcorrencia1);
        tipoOcorrenciaRepository.save(tipoOcorrencia2);
    }

    @Test
    public void testFindByIdTipoOcorrencia() {
        Integer idTipoOcorrencia = 1;
        Optional<TipoOcorrencia> result = tipoOcorrenciaRepository.findByIdTipoOcorrencia(idTipoOcorrencia);

        assertThat(result).isPresent();
        assertThat(result.get().getIdTipoOcorrencia()).isEqualTo(idTipoOcorrencia);
        assertThat(result.get().getNome()).isEqualTo("Incident Type 1");

        Optional<TipoOcorrencia> nonExistentResult = tipoOcorrenciaRepository.findByIdTipoOcorrencia(999);
        assertThat(nonExistentResult).isNotPresent();
    }
}