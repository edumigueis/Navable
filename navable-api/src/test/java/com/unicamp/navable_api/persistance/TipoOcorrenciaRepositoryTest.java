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

    private Integer idTipoOcorrenciaSalvo;

    @BeforeEach
    public void setUp() {
        TipoOcorrencia tipoOcorrencia1 = new TipoOcorrencia(null, "Incident Type 1", "a");
        tipoOcorrenciaRepository.save(tipoOcorrencia1);
        tipoOcorrenciaRepository.flush();

        idTipoOcorrenciaSalvo = tipoOcorrencia1.getIdTipoOcorrencia();
    }

    @Test
    public void testFindByIdTipoOcorrencia() {
        Optional<TipoOcorrencia> result = tipoOcorrenciaRepository.findById(idTipoOcorrenciaSalvo);

        assertThat(result).isPresent();
        assertThat(result.get().getIdTipoOcorrencia()).isEqualTo(idTipoOcorrenciaSalvo);
        assertThat(result.get().getNome()).isEqualTo("Incident Type 1");

        Optional<TipoOcorrencia> nonExistentResult = tipoOcorrenciaRepository.findById(999);
        assertThat(nonExistentResult).isNotPresent();
    }
}
