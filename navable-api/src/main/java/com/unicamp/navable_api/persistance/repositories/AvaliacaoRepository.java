package com.unicamp.navable_api.persistance.repositories;

import com.unicamp.navable_api.persistance.entities.Avaliacao;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Integer> {

    // Query to get evaluations for a specific establishment
    @Query(value = """
            SELECT a.*
            FROM Avaliacao a
            WHERE a.id_estabelecimento = :id_estabelecimento
            """, nativeQuery = true)
    List<Avaliacao> findByEstabelecimentoId(@Param("id_estabelecimento") Integer idEstabelecimento);

    // Query to get evaluations for a specific user
    @Query(value = """
            SELECT a.*
            FROM Avaliacao a
            WHERE a.id_usuario = :id_usuario
            """, nativeQuery = true)
    List<Avaliacao> findByUsuarioId(@Param("id_usuario") Integer idUsuario);

    // Query to get average ratings per establishment
    @Query(value = """
            SELECT e.id_estabelecimento, AVG(a.nota) AS media_avaliacoes
            FROM Estabelecimento e
            LEFT JOIN Avaliacao a ON e.id_estabelecimento = a.id_estabelecimento
            GROUP BY e.id_estabelecimento
            """, nativeQuery = true)
    List<Object[]> findAverageAvaliacaoByEstabelecimento();
}

