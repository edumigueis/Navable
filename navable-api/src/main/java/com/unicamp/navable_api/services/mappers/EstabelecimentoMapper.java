package com.unicamp.navable_api.services.mappers;

import com.unicamp.navable_api.api.model.EstabelecimentoDTO;
import com.unicamp.navable_api.persistance.entities.Estabelecimento;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EstabelecimentoMapper {

    EstabelecimentoMapper INSTANCE = Mappers.getMapper(EstabelecimentoMapper.class);

    Estabelecimento toEntity(EstabelecimentoDTO estabelecimentoDTO);

    EstabelecimentoDTO toDTO(Estabelecimento estabelecimento);
}
