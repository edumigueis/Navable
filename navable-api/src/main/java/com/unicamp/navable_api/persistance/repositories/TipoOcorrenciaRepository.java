package com.unicamp.navable_api.persistance.repositories;

import com.unicamp.navable_api.persistance.entities.TipoOcorrencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TipoOcorrenciaRepository extends JpaRepository<TipoOcorrencia, Long> {
    Optional<TipoOcorrencia> findByIdTipoOcorrencia(Integer idTipoOcorrencia);
}
