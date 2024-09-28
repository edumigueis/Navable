package com.unicamp.navable_api.persistance.repositories;

import com.unicamp.navable_api.persistance.entities.CategoriaAcessibilidade;
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
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    // Query to find selos for a specific user
    @Query(value = """
        SELECT s.*, su.timestamp
        FROM Selo s
        JOIN SeloUsuario su ON s.id_selo = su.id_selo
        WHERE su.id_usuario = :id_usuario
        """, nativeQuery = true)
    List<Object[]> findSelosByUsuarioId(@Param("id_usuario") Integer idUsuario);

    // Query to get accessibility categories for a specific user
    @Query(value = """
        SELECT ca.*
        FROM CategoriaAcessibilidade ca
        JOIN UsuarioCategoria uc ON ca.categoria_ac_id = uc.categoria_ac_id
        WHERE uc.id_usuario = :id_usuario
        """, nativeQuery = true)
    List<CategoriaAcessibilidade> findByUsuarioId(@Param("id_usuario") Integer idUsuario);


    @Modifying
    @Transactional
    default Usuario createUsuario(Usuario usuario, EntityManager entityManager) {
        entityManager.persist(usuario);
        return usuario;
    }
}
