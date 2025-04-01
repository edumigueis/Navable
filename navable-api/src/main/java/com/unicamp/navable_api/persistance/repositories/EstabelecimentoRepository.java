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
            SELECT e.id_estabelecimento,
                   e.id_tipo_estabeleci,
                   e.nome,
                   e.latitude,
                   e.longitude,
                   e.imagem,
                   e.endereco,
                   COALESCE(AVG(a.nota), 0) AS nota
            FROM estabelecimento e
            LEFT JOIN avaliacao a ON e.id_estabelecimento = a.id_estabelecimento
            WHERE (
                6371 * acos(
                    cos(radians(:latitude)) * cos(radians(e.latitude)) *
                    cos(radians(e.longitude) - radians(:longitude)) +
                    sin(radians(:latitude)) * sin(radians(e.latitude))
                )
            ) <= 1
            GROUP BY e.id_estabelecimento
            """, nativeQuery = true)
    List<Object[]> findNearby(@Param("latitude") double latitude, @Param("longitude") double longitude);

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

    // Query to search establishments by name
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
