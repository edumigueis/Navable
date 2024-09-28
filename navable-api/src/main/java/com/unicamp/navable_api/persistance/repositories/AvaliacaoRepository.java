package com.unicamp.navable_api.persistance.repositories;

import com.unicamp.navable_api.persistance.entities.Avaliacao;
import com.unicamp.navable_api.persistance.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
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

