package com.unicamp.navable_api.services.impl;

import com.unicamp.navable_api.api.model.EstabelecimentoDTO;
import com.unicamp.navable_api.persistance.entities.Estabelecimento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EstabelecimentoService {

    @Autowired
    private EstabelecimentoRepository estabelecimentoRepository;

    @Autowired
    private ModelMapper modelMapper;

    public EstabelecimentoDTO createEstabelecimento(EstabelecimentoDTO estabelecimentoDTO) {
        Estabelecimento estabelecimento = modelMapper.map(estabelecimentoDTO, Estabelecimento.class);
        Estabelecimento savedEstabelecimento = estabelecimentoRepository.save(estabelecimento);
        return modelMapper.map(savedEstabelecimento, EstabelecimentoDTO.class);
    }

    public List<EstabelecimentoDTO> getAllEstabelecimentos() {
        List<Estabelecimento> estabelecimentos = estabelecimentoRepository.findAll();
        return estabelecimentos.stream()
                .map(estabelecimento -> modelMapper.map(estabelecimento, EstabelecimentoDTO.class))
                .collect(Collectors.toList());
    }

    public EstabelecimentoDTO getEstabelecimentoById(Integer id) {
        Estabelecimento estabelecimento = estabelecimentoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Estabelecimento not found with id " + id));
        return modelMapper.map(estabelecimento, EstabelecimentoDTO.class);
    }

    public void deleteEstabelecimento(Integer id) {
        estabelecimentoRepository.deleteById(id);
    }

    public List<EstabelecimentoDTO> getEstabelecimentosByTipo(Integer tipoId) {
        // Implement logic to get establishments by type
        return null; // Placeholder for actual implementation
    }
}

