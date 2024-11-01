package com.unicamp.navable_api.services.impl;

import com.unicamp.navable_api.api.model.*;
import com.unicamp.navable_api.persistance.entities.*;
import com.unicamp.navable_api.persistance.repositories.UsuarioRepository;
import com.unicamp.navable_api.services.mappers.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
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
    private final SelosMapper selosMapper = SelosMapper.INSTANCE;
    private final CategoriaAcessibilidadeMapper categoriaMapper = CategoriaAcessibilidadeMapper.INSTANCE;

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

    public List<SeloDTO> getSelosByUserId(Integer id) {
        List<Selo> selos = usuarioRepository.findSelosByUsuario(id);
        return selos.stream()
                .map(selosMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<CategoriaAcessibilidadeDTO> getCategoriasByUserId(Integer id) {
        List<CategoriaAcessibilidade> selos = usuarioRepository.findCategoriasByUsuario(id);
        return selos.stream()
                .map(categoriaMapper::toDTO)
                .collect(Collectors.toList());
    }

    public UsuarioDTO getUsuarioById(Integer id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuario not found with id " + id));
        return usuarioMapper.toDTO(usuario);
    }

    public UsuarioDTO signIn(String email, String password) throws AuthenticationException {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("User not found with email " + email));

        if (password.matches(usuario.getSenha())) {
            return usuarioMapper.toDTO(usuario);
        } else {
            throw new AuthenticationException();
        }
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
