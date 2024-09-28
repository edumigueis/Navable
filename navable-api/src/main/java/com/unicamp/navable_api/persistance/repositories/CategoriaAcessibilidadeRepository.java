package com.unicamp.navable_api.persistance.repositories;

import com.unicamp.navable_api.persistance.entities.CategoriaAcessibilidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaAcessibilidadeRepository extends JpaRepository<CategoriaAcessibilidade, Integer> {
}