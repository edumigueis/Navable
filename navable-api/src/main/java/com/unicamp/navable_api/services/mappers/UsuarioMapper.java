package com.unicamp.navable_api.services.mappers;


import com.unicamp.navable_api.api.model.UsuarioDTO;
import com.unicamp.navable_api.persistance.entities.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UsuarioMapper {

    UsuarioMapper INSTANCE = Mappers.getMapper(UsuarioMapper.class);

    Usuario toEntity(UsuarioDTO usuarioDTO);

    UsuarioDTO toDTO(Usuario usuario);
}