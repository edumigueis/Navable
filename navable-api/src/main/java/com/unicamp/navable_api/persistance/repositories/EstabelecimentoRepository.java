package com.unicamp.navable_api.persistance.repositories;

import com.unicamp.navable_api.persistance.entities.Estabelecimento;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EstabelecimentoRepository extends JpaRepository<Estabelecimento, Integer> {

    // Query to find establishments within 1km of a given latitude and longitude
    @Query(value = """
            SELECT e.*
            FROM Estabelecimento e
            WHERE (
                6371 * acos(
                    cos(radians(:latitude)) * cos(radians(e.latitude)) *
                    cos(radians(e.longitude) - radians(:longitude)) +
                    sin(radians(:latitude)) * sin(radians(e.latitude))
                )
            ) <= 1
            """, nativeQuery = true)
    List<Estabelecimento> findNearby(@Param("latitude") double latitude, @Param("longitude") double longitude);

    // Query to find establishments by rating, category, and type
    @Query(value = """
            SELECT e.*
            FROM Estabelecimento e
            JOIN Avaliacao a ON e.id_estabelecimento = a.id_estabelecimento
            JOIN CategoriaAceTipoEstab cat ON e.id_tipo_estabeleci = cat.id_tipo_estabeleci
            WHERE (a.nota = :nota OR :nota IS NULL)
            AND (cat.categoria_ac_id IN (:lista_categoria) OR :lista_categoria IS NULL)
            AND (e.id_tipo_estabeleci = :id_tipo_estabeleci OR :id_tipo_estabeleci IS NULL)
            """, nativeQuery = true)
    List<Estabelecimento> findByNotaAndCategoriaAndTipo(@Param("nota") Float nota, @Param("lista_categoria") List<Integer> listaCategoria, @Param("id_tipo_estabeleci") Integer idTipoEstabeleci);

    // Query to find establishments within latitude/longitude bounds
    @Query(value = """
            SELECT e.*
            FROM Estabelecimento e
            WHERE (e.latitude BETWEEN :lat_min AND :lat_max)
            AND (e.longitude BETWEEN :lon_min AND :lon_max)
            AND (e.id_tipo_estabeleci = :id_tipo_estabeleci OR :id_tipo_estabeleci IS NULL)
            """, nativeQuery = true)
    List<Estabelecimento> findByLatLongBounds(
            @Param("lat_min") double latMin,
            @Param("lat_max") double latMax,
            @Param("lon_min") double lonMin,
            @Param("lon_max") double lonMax,
            @Param("id_tipo_estabeleci") Long idTipoEstabeleci);

    // Query to search establishments by name
    @Query(value = """
            SELECT e.*
            FROM Estabelecimento e
            WHERE e.nome ILIKE '%' || :nome || '%' OR :nome IS NULL
            """, nativeQuery = true)
    List<Estabelecimento> findByNome(@Param("nome") String nome);
}
