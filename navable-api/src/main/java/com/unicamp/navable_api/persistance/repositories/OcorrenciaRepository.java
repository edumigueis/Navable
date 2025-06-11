package com.unicamp.navable_api.persistance.repositories;

import com.unicamp.navable_api.persistance.entities.Ocorrencia;
import com.unicamp.navable_api.persistance.support.GeoLocationSupport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OcorrenciaRepository extends JpaRepository<Ocorrencia, Integer> {
    @Query(value = "SELECT o.* FROM Ocorrencia o WHERE " + 
           GeoLocationSupport.OCORRENCIA_HAVERSINE_DISTANCE, 
           nativeQuery = true)
    List<Ocorrencia> findNearby(
        @Param("latitude") double latitude,
        @Param("longitude") double longitude,
        @Param("distance") double distance
    );
    
    // Providing a convenience method with default distance
    default List<Ocorrencia> findNearby(double latitude, double longitude) {
        return findNearby(latitude, longitude, GeoLocationSupport.DEFAULT_SEARCH_RADIUS_KM);
    }

    @Query(value = """
        SELECT o.*, COUNT(v.id_usuario) AS total_votos
        FROM Ocorrencia o
        LEFT JOIN Votos v ON o.id_ocorrencia = v.id_ocorrencia
        WHERE o.id_ocorrencia = :id_ocorrencia
        GROUP BY o.id_ocorrencia
        """, nativeQuery = true)
    List<Object[]> findOcorrenciaWithVoteCount(@Param("id_ocorrencia") Integer idOcorrencia);
}

