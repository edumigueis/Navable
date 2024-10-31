package com.unicamp.navable_api.services.mappers;

import com.unicamp.navable_api.api.model.OcorrenciaDTO;
import com.unicamp.navable_api.api.model.TipoOcorrenciaDTO;
import com.unicamp.navable_api.persistance.entities.Ocorrencia;
import com.unicamp.navable_api.persistance.entities.TipoOcorrencia;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TipoOcorrenciaMapper {

    TipoOcorrenciaMapper INSTANCE = Mappers.getMapper(TipoOcorrenciaMapper.class);

    // Mapeamento de OcorrenciaDTO para Ocorrencia (Entidade)
    TipoOcorrencia toEntity(TipoOcorrenciaDTO ocorrenciaDTO);

    // Mapeamento de Ocorrencia (Entidade) para OcorrenciaDTO
    TipoOcorrenciaDTO toDTO(TipoOcorrencia ocorrencia);
}
