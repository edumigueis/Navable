package com.unicamp.navable_api.services.mappers;

import com.unicamp.navable_api.api.model.OcorrenciaDTO;
import com.unicamp.navable_api.persistance.entities.Ocorrencia;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OcorrenciaMapper {

    OcorrenciaMapper INSTANCE = Mappers.getMapper(OcorrenciaMapper.class);

    // Mapeamento de OcorrenciaDTO para Ocorrencia (Entidade)
    Ocorrencia toEntity(OcorrenciaDTO ocorrenciaDTO);

    // Mapeamento de Ocorrencia (Entidade) para OcorrenciaDTO
    OcorrenciaDTO toDTO(Ocorrencia ocorrencia);
}

