package com.firefly.core.lending.factoring.core.mappers.advance.v1;

import com.firefly.core.lending.factoring.interfaces.dtos.advance.v1.FactoringAdvanceDTO;
import com.firefly.core.lending.factoring.models.entities.advance.v1.FactoringAdvance;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FactoringAdvanceMapper {
    FactoringAdvanceDTO toDTO(FactoringAdvance advance);
    FactoringAdvance toEntity(FactoringAdvanceDTO dto);
}
