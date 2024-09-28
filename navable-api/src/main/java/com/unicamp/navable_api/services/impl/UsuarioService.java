package com.unicamp.navable_api.services.impl;

import com.unicamp.navable_api.api.model.AvaliacaoDTO;
import com.unicamp.navable_api.api.model.UsuarioDTO;
import com.unicamp.navable_api.persistance.entities.Usuario;
import com.unicamp.navable_api.persistance.repositories.UsuarioRepository;
import com.unicamp.navable_api.services.mappers.UsuarioMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    private final UsuarioMapper usuarioMapper = UsuarioMapper.INSTANCE;

    public UsuarioDTO createUsuario(UsuarioDTO usuarioDTO) {
        Usuario usuario = usuarioMapper.toEntity(usuarioDTO);
        Usuario savedUsuario = usuarioRepository.save(usuario);
        return usuarioMapper.toDTO(savedUsuario);
    }

    public List<UsuarioDTO> getAllUsuarios() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarios.stream()
                .map(usuarioMapper::toDTO)
                .collect(Collectors.toList());
    }

    public UsuarioDTO getUsuarioById(Integer id) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Usuario not found with id " + id));
        return usuarioMapper.toDTO(usuario);
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

    public AvaliacaoDTO createAvaliacao(Integer usuarioId, AvaliacaoDTO avaliacaoDTO) {
        // Implement logic to create an evaluation by user
        return null; // Placeholder for actual implementation
    }

    public void addCategoriaToUsuario(Integer usuarioId, List<Integer> categoriaIds) {
        // Implement logic to add accessibility categories to user
    }

    public UsuarioDTO updateUsuario(Integer id, UsuarioDTO usuarioDTO) {
        //Usuario usuario = usuarioRepository.find(id);
        //return usuarioMapper.toDTO(usuario);
        return new UsuarioDTO();
    }
}
