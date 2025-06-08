package com.unicamp.navable_api.services.impl;

import com.unicamp.navable_api.api.model.CategoriaAcessibilidadeDTO;
import com.unicamp.navable_api.persistance.entities.CategoriaAcessibilidade;
import com.unicamp.navable_api.persistance.repositories.CategoriaAcessibilidadeRepository;
import com.unicamp.navable_api.services.mappers.CategoriaAcessibilidadeMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class CategoriaAcessibilidadeService {

    @Autowired
    private CategoriaAcessibilidadeRepository categoriaRepository;

    private static final CategoriaAcessibilidadeMapper categoriaMapper = CategoriaAcessibilidadeMapper.INSTANCE;

    public List<CategoriaAcessibilidadeDTO> getAllCategorias() {
        List<CategoriaAcessibilidade> categorias = categoriaRepository.findAll();
        if (categorias.isEmpty()) {
            throw new IllegalArgumentException("No categories found");
        }
        return categorias.stream()
                .map(categoriaMapper::toDTO)
                .toList();
    }

    public CategoriaAcessibilidadeDTO getCategoriaById(Integer id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Invalid Categoria ID");
        }
        CategoriaAcessibilidade categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Categoria not found with id " + id));
        return categoriaMapper.toDTO(categoria);
    }

    public CategoriaAcessibilidadeDTO updateCategoria(Integer id, CategoriaAcessibilidadeDTO categoria) {
        if (id <= 0) {
            throw new IllegalArgumentException("Invalid Categoria ID");
        }

        CategoriaAcessibilidade categoriaEntity = categoriaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Categoria not found with id " + id));

        if (categoria.getNome() == null || categoria.getNome().isEmpty()) {
            throw new IllegalArgumentException("Categoria name cannot be null or empty");
        }

        Optional.of(categoria.getNome()).ifPresent(categoriaEntity::setNome);
        Optional.ofNullable(categoria.getGrupo()).ifPresent(categoriaEntity::setGrupo);

        categoriaRepository.save(categoriaEntity);
        return categoriaMapper.toDTO(categoriaEntity);
    }
}
