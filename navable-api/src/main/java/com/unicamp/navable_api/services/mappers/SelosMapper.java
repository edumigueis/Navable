package com.unicamp.navable_api.services.mappers;

import com.unicamp.navable_api.api.model.*;
import com.unicamp.navable_api.persistance.entities.*;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SelosMapper {

    SelosMapper INSTANCE = Mappers.getMapper(SelosMapper.class);

    Selo toEntity(SeloDTO seloDTO);
    SeloDTO toDTO(Selo selo);
}
