package com.unicamp.navable_api.services.mappers;

import com.unicamp.navable_api.api.model.CategoriaAcessibilidadeDTO;
import com.unicamp.navable_api.persistance.entities.CategoriaAcessibilidade;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CategoriaAcessibilidadeMapper {

    CategoriaAcessibilidadeMapper INSTANCE = Mappers.getMapper(CategoriaAcessibilidadeMapper.class);

    CategoriaAcessibilidade toEntity(CategoriaAcessibilidadeDTO categoriaDTO);

    CategoriaAcessibilidadeDTO toDTO(CategoriaAcessibilidade categoria);
}

