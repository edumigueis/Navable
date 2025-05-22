package com.unicamp.navable_api.persistance.repositories;

import com.unicamp.navable_api.persistance.entities.Estabelecimento;
import com.unicamp.navable_api.persistance.support.GeoLocationSupport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EstabelecimentoRepository extends JpaRepository<Estabelecimento, Integer> {
    // Revised to use constant
    @Query(value = "SELECT e.id_estabelecimento, " +
           "e.id_tipo_estabeleci, " +
           "e.nome, " +
           "e.latitude, " +
           "e.longitude, " +
           "e.imagem, " +
           "e.endereco, " +
           "AVG(a.nota) AS nota " +
           "FROM estabelecimento e " +
           "LEFT JOIN avaliacao a ON e.id_estabelecimento = a.id_estabelecimento " +
           "WHERE " + GeoLocationSupport.ESTABELECIMENTO_HAVERSINE_DISTANCE + " " +
           "GROUP BY e.id_estabelecimento", 
           nativeQuery = true)
    List<Object[]> findNearbyWithRatings(
        @Param("latitude") double latitude,
        @Param("longitude") double longitude,
        @Param("distance") double distance
    );
    
    // Providing a convenience method with default distance
    default List<Object[]> findNearbyWithRatings(double latitude, double longitude) {
        return findNearbyWithRatings(latitude, longitude, GeoLocationSupport.DEFAULT_SEARCH_RADIUS_KM);
    }

    @Query(value = """
            SELECT e.*
            FROM Estabelecimento e
            JOIN Avaliacao a ON e.id_estabelecimento = a.id_estabelecimento
            JOIN CategoriaAceTipoEstab cat ON e.id_tipo_estabeleci = cat.id_tipo_estabeleci
            WHERE (a.nota = :nota OR :nota IS NULL)
            AND (:lista_categoria IS NULL OR cat.categoria_ac_id IN (:lista_categoria))
            AND (:id_tipo_estabeleci IS NULL OR e.id_tipo_estabeleci = :id_tipo_estabeleci)
            """, nativeQuery = true)
    List<Estabelecimento> findByNotaAndCategoriaAndTipo(@Param("nota") Float nota,
                                                         @Param("lista_categoria") List<Integer> listaCategoria,
                                                         @Param("id_tipo_estabeleci") Integer idTipoEstabeleci);

    @Query(value = """
            SELECT e.*
            FROM Estabelecimento e
            WHERE e.nome ILIKE '%' || :nome || '%' OR :nome IS NULL
            """, nativeQuery = true)
    List<Estabelecimento> findByNome(@Param("nome") String nome);

    @Query(value = """
            SELECT AVG(a.nota)
            FROM avaliacao a
            WHERE a.id_estabelecimento = :idEstabelecimento
            """, nativeQuery = true)
    Double findAverageNotaByEstabelecimentoId(@Param("idEstabelecimento") Integer idEstabelecimento);
}
