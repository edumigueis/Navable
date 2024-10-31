package com.unicamp.navable_api.persistance.repositories;

import com.unicamp.navable_api.persistance.entities.Ocorrencia;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OcorrenciaRepository extends JpaRepository<Ocorrencia, Integer> {

    @Query(value = """
            SELECT o.*
            FROM Ocorrencia o
            WHERE (
                6371 * acos(
                    cos(radians(:latitude)) * cos(radians(o.latitude)) *
                    cos(radians(o.longitude) - radians(:longitude)) +
                    sin(radians(:latitude)) * sin(radians(o.latitude))
                )
            ) <= 1
            """, nativeQuery = true)
    List<Ocorrencia> findNearby(@Param("latitude") double latitude, @Param("longitude") double longitude);

    // Query to get occurrence details and count of votes
    @Query(value = """
            SELECT o.*, COUNT(v.id_usuario) AS total_votos
            FROM Ocorrencia o
            LEFT JOIN Votos v ON o.id_ocorrencia = v.id_ocorrencia
            WHERE o.id_ocorrencia = :id_ocorrencia
            GROUP BY o.id_ocorrencia
            """, nativeQuery = true)
    List<Object[]> findOcorrenciaWithVoteCount(@Param("id_ocorrencia") Integer idOcorrencia);
}

