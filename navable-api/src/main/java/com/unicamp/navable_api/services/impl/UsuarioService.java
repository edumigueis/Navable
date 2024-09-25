package com.unicamp.navable_api.services.impl;

import com.unicamp.navable_api.api.model.UsuarioDTO;
import com.unicamp.navable_api.persistance.entities.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ModelMapper modelMapper; // Assuming you're using ModelMapper for DTO conversion

    public UsuarioDTO createUsuario(UsuarioDTO usuarioDTO) {
        Usuario usuario = modelMapper.map(usuarioDTO, Usuario.class);
        Usuario savedUsuario = usuarioRepository.save(usuario);
        return modelMapper.map(savedUsuario, UsuarioDTO.class);
    }

    public List<UsuarioDTO> getAllUsuarios() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarios.stream()
                .map(usuario -> modelMapper.map(usuario, UsuarioDTO.class))
                .collect(Collectors.toList());
    }

    public UsuarioDTO getUsuarioById(Integer id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario not found with id " + id));
        return modelMapper.map(usuario, UsuarioDTO.class);
    }

    public void deleteUsuario(Integer id) {
        usuarioRepository.deleteById(id);
    }

    public void addSeloToUsuario(Integer usuarioId, Integer seloId) {
        // Implement logic to associate selo to usuario
    }

    public void voteOnOcorrencia(Integer usuarioId, Integer ocorrenciaId) {
        // Implement logic to record vote on an occurrence
    }

    public OcorrenciaDTO createOcorrencia(Integer usuarioId, OcorrenciaDTO ocorrenciaDTO) {
        // Implement logic to create an occurrence by user
        return null; // Placeholder for actual implementation
    }

    public AvaliacaoDTO createAvaliacao(Integer usuarioId, AvaliacaoDTO avaliacaoDTO) {
        // Implement logic to create an evaluation by user
        return null; // Placeholder for actual implementation
    }

    public void addCategoriaToUsuario(Integer usuarioId, List<Integer> categoriaIds) {
        // Implement logic to add accessibility categories to user
    }
}
