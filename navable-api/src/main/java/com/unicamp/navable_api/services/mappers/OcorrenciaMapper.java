package com.unicamp.navable_api.services.mappers;

import com.unicamp.navable_api.api.model.OcorrenciaDTO;
import com.unicamp.navable_api.persistance.entities.Ocorrencia;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OcorrenciaMapper {

    OcorrenciaMapper INSTANCE = Mappers.getMapper(OcorrenciaMapper.class);

    Ocorrencia toEntity(OcorrenciaDTO ocorrenciaDTO);
    OcorrenciaDTO toDTO(Ocorrencia ocorrencia);
}

