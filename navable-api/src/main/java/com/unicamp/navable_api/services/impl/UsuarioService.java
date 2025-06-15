package com.unicamp.navable_api.services.impl;

import com.unicamp.navable_api.api.model.*;
import com.unicamp.navable_api.persistance.entities.*;
import com.unicamp.navable_api.persistance.repositories.UsuarioRepository;
import com.unicamp.navable_api.services.exceptions.*;
import com.unicamp.navable_api.services.mappers.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    private final UsuarioMapper usuarioMapper = UsuarioMapper.INSTANCE;
    private final SelosMapper selosMapper = SelosMapper.INSTANCE;
    private final CategoriaAcessibilidadeMapper categoriaMapper = CategoriaAcessibilidadeMapper.INSTANCE;

    public UsuarioDTO createUsuario(UsuarioDTO usuarioDTO) {
        if (usuarioDTO.getNome() == null || usuarioDTO.getNome().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }

        if (usuarioDTO.getEmail() == null || !usuarioDTO.getEmail().matches("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$")) {
            throw new IllegalArgumentException("Invalid email format");
        }

        if (usuarioDTO.getSenha() == null || usuarioDTO.getSenha().length() < 3) {
            throw new IllegalArgumentException("Password is too short");
        }

        Usuario usuario = usuarioMapper.toEntity(usuarioDTO);
        Usuario savedUsuario = usuarioRepository.save(usuario);
        return usuarioMapper.toDTO(savedUsuario);
    }

    public List<UsuarioDTO> getAllUsuarios() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarios.stream()
                .map(usuarioMapper::toDTO)
                .toList();
    }

    public List<SeloDTO> getSelosByUserId(Integer id) {
        List<Selo> selos = usuarioRepository.findSelosByUsuario(id);
        if (selos == null || selos.isEmpty()) {
            throw new IllegalArgumentException("No selos found for user with id " + id);
        }
        return selos.stream()
                .map(selosMapper::toDTO)
                .toList();
    }

    public List<CategoriaAcessibilidadeDTO> getCategoriasByUserId(Integer id) {
        List<CategoriaAcessibilidade> categorias = usuarioRepository.findCategoriasByUsuario(id);
        if (categorias == null || categorias.isEmpty()) {
            throw new IllegalArgumentException("No categorias found for user with id " + id);
        }
        return categorias.stream()
                .map(categoriaMapper::toDTO)
                .toList();
    }

    public UsuarioDTO getUsuarioById(Integer id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Invalid ID");
        }

        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuario not found with id " + id));
        return usuarioMapper.toDTO(usuario);
    }

    @Transactional
    public void deleteUsuario(Integer id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Invalid ID");
        }
        usuarioRepository.deleteById(id);
    }

    @Transactional
    public void addSeloToUsuario(Integer usuarioId, Integer seloId) {
        if (usuarioId <= 0 || seloId <= 0) {
            throw new IllegalArgumentException("Invalid ID");
        }
        usuarioRepository.addSeloToUsuario(usuarioId, seloId);
    }

    @Transactional
    public void voteOnOcorrencia(Integer usuarioId, Integer ocorrenciaId) {
        if (usuarioId <= 0 || ocorrenciaId <= 0) {
            throw new IllegalArgumentException("Invalid ID");
        }
        usuarioRepository.voteOnOcorrencia(usuarioId, ocorrenciaId);
    }

    @Transactional
    public void addCategoriaToUsuario(Integer usuarioId, List<Integer> categoriaIds) {
        if (categoriaIds == null || categoriaIds.isEmpty()) {
            throw new IllegalArgumentException("Categoria list cannot be empty");
        }
        for (Integer categoriaId : categoriaIds) {
            if (categoriaId <= 0) {
                throw new IllegalArgumentException("Invalid categoria ID: " + categoriaId);
            }
            usuarioRepository.addCategoriaToUsuario(usuarioId, categoriaId);
        }
    }

    @Transactional
    public void updateCategoriasToUsuario(Integer usuarioId, List<Integer> categoriaIds) {
        if (categoriaIds == null || categoriaIds.isEmpty()) {
            throw new IllegalArgumentException("Categoria list cannot be empty");
        }

        usuarioRepository.deleteCategoriasByUsuarioId(usuarioId);
        for (Integer categoriaId : categoriaIds) {
            if (categoriaId <= 0) {
                throw new IllegalArgumentException("Invalid categoria ID: " + categoriaId);
            }
            usuarioRepository.addCategoriaToUsuario(usuarioId, categoriaId);
        }
    }

    public UsuarioDTO getUsuarioByEmail(String email) {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new UsuarioNoEncontradoException(email));
        return usuarioMapper.toDTO(usuario);
    }
}
