package com.unicamp.navable_api.services.impl;

import com.unicamp.navable_api.api.model.UsuarioDTO;
import com.unicamp.navable_api.persistance.entities.Usuario;
import com.unicamp.navable_api.persistance.repositories.UsuarioRepository;
import com.unicamp.navable_api.services.mappers.UsuarioMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private EntityManager entityManager;

    private final UsuarioMapper usuarioMapper = UsuarioMapper.INSTANCE;

    public UsuarioDTO createUsuario(UsuarioDTO usuarioDTO) {
        Usuario usuario = usuarioMapper.toEntity(usuarioDTO);
        Usuario savedUsuario = usuarioRepository.createUsuario(usuario, entityManager);
        return usuarioMapper.toDTO(savedUsuario);
    }

    public List<UsuarioDTO> getAllUsuarios() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarios.stream()
                .map(usuarioMapper::toDTO)
                .collect(Collectors.toList());
    }

    public UsuarioDTO getUsuarioById(Integer id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuario not found with id " + id));
        return usuarioMapper.toDTO(usuario);
    }

    @Transactional
    public void deleteUsuario(Integer id) {
        usuarioRepository.deleteById(id);
    }

    @Transactional
    public void addSeloToUsuario(Integer usuarioId, Integer seloId) {
        usuarioRepository.addSeloToUsuario(usuarioId, seloId);
    }

    @Transactional
    public void voteOnOcorrencia(Integer usuarioId, Integer ocorrenciaId) {
        usuarioRepository.voteOnOcorrencia(usuarioId, ocorrenciaId);
    }

    @Transactional
    public void addCategoriaToUsuario(Integer usuarioId, List<Integer> categoriaIds) {
        for (Integer categoriaId : categoriaIds) {
            usuarioRepository.addCategoriaToUsuario(usuarioId, categoriaId);
        }
    }
}
