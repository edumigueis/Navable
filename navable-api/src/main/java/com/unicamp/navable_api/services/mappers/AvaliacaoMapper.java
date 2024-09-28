package com.unicamp.navable_api.services.mappers;

import com.unicamp.navable_api.api.model.AvaliacaoDTO;
import com.unicamp.navable_api.persistance.entities.Avaliacao;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AvaliacaoMapper {

    AvaliacaoMapper INSTANCE = Mappers.getMapper(AvaliacaoMapper.class);

    Avaliacao toEntity(AvaliacaoDTO avaliacaoDTO);

    AvaliacaoDTO toDTO(Avaliacao avaliacao);
}

