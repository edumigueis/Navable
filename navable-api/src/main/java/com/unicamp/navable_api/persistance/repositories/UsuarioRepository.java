package com.unicamp.navable_api.persistance.repositories;

import com.unicamp.navable_api.persistance.entities.CategoriaAcessibilidade;
import com.unicamp.navable_api.persistance.entities.Selo;
import com.unicamp.navable_api.persistance.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    @Query(value = """
            SELECT s.*, su.timestamp
            FROM Selo s
            JOIN SeloUsuario su ON s.id_selo = su.id_selo
            WHERE su.id_usuario = :id_usuario
            """, nativeQuery = true)
    List<Object[]> findSelosByUsuarioId(@Param("id_usuario") Integer idUsuario);

    @Query(value = """
            SELECT ca.*
            FROM CategoriaAcessibilidade ca
            JOIN UsuarioCategoria uc ON ca.categoria_ac_id = uc.categoria_ac_id
            WHERE uc.id_usuario = :id_usuario
            """, nativeQuery = true)
    List<CategoriaAcessibilidade> findByUsuarioId(@Param("id_usuario") Integer idUsuario);

    @Query(value = """
            SELECT s.*, su.timestamp
            FROM selo AS s
            JOIN selo_usuario AS su ON s.id_selo = su.id_selo
            WHERE su.id_usuario = :id_usuario
                        """, nativeQuery = true)
    List<Selo> findSelosByUsuario(@Param("id_usuario") Integer idUsuario);

    @Query(value = """
            SELECT ca.*
            FROM categoria_acessibilidade AS ca
            JOIN usuario_categoria AS uc ON ca.categoria_ac_id = uc.categoria_ac_id
            WHERE uc.id_usuario = :id_usuario
            """, nativeQuery = true)
    List<CategoriaAcessibilidade> findCategoriasByUsuario(@Param("id_usuario") Integer idUsuario);

    Optional<Usuario> findByEmail(String email);

    @Modifying
    @Transactional
    void deleteById(Integer id);

    @Modifying
    @Query(value = "INSERT INTO UsuarioSelo (usuario_id, selo_id) VALUES (:usuarioId, :seloId)", nativeQuery = true)
    void addSeloToUsuario(@Param("usuarioId") Integer usuarioId, @Param("seloId") Integer seloId);

    @Modifying
    @Query(value = "INSERT INTO OcorrenciaVote (usuario_id, ocorrencia_id) VALUES (:usuarioId, :ocorrenciaId)", nativeQuery = true)
    void voteOnOcorrencia(@Param("usuarioId") Integer usuarioId, @Param("ocorrenciaId") Integer ocorrenciaId);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO Avaliacao (usuario_id, descricao, rating) VALUES (:usuarioId, :descricao, :rating)", nativeQuery = true)
    void createAvaliacao(@Param("usuarioId") Integer usuarioId, @Param("descricao") String descricao,
                         @Param("rating") Integer rating);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO UsuarioCategoria (usuario_id, categoria_id) VALUES (:usuarioId, :categoriaId)", nativeQuery = true)
    void addCategoriaToUsuario(@Param("usuarioId") Integer usuarioId, @Param("categoriaId") Integer categoriaId);
}
