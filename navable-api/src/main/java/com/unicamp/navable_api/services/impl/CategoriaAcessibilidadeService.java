package com.unicamp.navable_api.services.impl;

import com.unicamp.navable_api.api.model.CategoriaAcessibilidadeDTO;
import com.unicamp.navable_api.persistance.entities.CategoriaAcessibilidade;
import com.unicamp.navable_api.persistance.repositories.CategoriaAcessibilidadeRepository;
import com.unicamp.navable_api.services.mappers.CategoriaAcessibilidadeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoriaAcessibilidadeService {

    @Autowired
    private CategoriaAcessibilidadeRepository categoriaRepository;

    private final CategoriaAcessibilidadeMapper categoriaMapper = CategoriaAcessibilidadeMapper.INSTANCE;

    public CategoriaAcessibilidadeDTO createCategoria(CategoriaAcessibilidadeDTO categoriaDTO) {
        // Usar o mapper para converter DTO para Entidade
        CategoriaAcessibilidade categoria = categoriaMapper.toEntity(categoriaDTO);
        CategoriaAcessibilidade savedCategoria = categoriaRepository.save(categoria);
        // Retornar o DTO a partir da entidade salva
        return categoriaMapper.toDTO(savedCategoria);
    }

    public List<CategoriaAcessibilidadeDTO> getAllCategorias() {
        List<CategoriaAcessibilidade> categorias = categoriaRepository.findAll();
        return categorias.stream()
                .map(categoriaMapper::toDTO)
                .collect(Collectors.toList());
    }

    public CategoriaAcessibilidadeDTO getCategoriaById(Integer id) {
        CategoriaAcessibilidade categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Categoria not found with id " + id));
        return categoriaMapper.toDTO(categoria);
    }

}
