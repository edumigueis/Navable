package com.unicamp.navable_api.services.impl;

import com.unicamp.navable_api.api.model.CategoriaAcessibilidadeDTO;
import com.unicamp.navable_api.persistance.entities.CategoriaAcessibilidade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoriaAcessibilidadeService {

    @Autowired
    private CategoriaAcessibilidadeRepository categoriaRepository;

    @Autowired
    private ModelMapper modelMapper;

    public CategoriaAcessibilidadeDTO createCategoria(CategoriaAcessibilidadeDTO categoriaDTO) {
        CategoriaAcessibilidade categoria = modelMapper.map(categoriaDTO, CategoriaAcessibilidade.class);
        CategoriaAcessibilidade savedCategoria = categoriaRepository.save(categoria);
        return modelMapper.map(savedCategoria, CategoriaAcessibilidadeDTO.class);
    }

    public List<CategoriaAcessibilidadeDTO> getAllCategorias() {
        List<CategoriaAcessibilidade> categorias = categoriaRepository.findAll();
        return categorias.stream()
                .map(categoria -> modelMapper.map(categoria, CategoriaAcessibilidadeDTO.class))
                .collect(Collectors.toList());
    }

    public CategoriaAcessibilidadeDTO getCategoriaById(Integer id) {
        CategoriaAcessibilidade categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categoria not found with id " + id));
        return modelMapper.map(categoria, CategoriaAcessibilidadeDTO.class);
    }

    public void deleteCategoria(Integer id) {
        categoriaRepository.deleteById(id);
    }

    public List<TipoDeEstabelecimentoDTO> getTiposByCategoria(Integer categoriaId) {
        // Implement logic to get types by category
        return null; // Placeholder for actual implementation
    }
}
