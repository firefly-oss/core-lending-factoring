package com.firefly.core.lending.factoring.core.mappers.fee.v1;

import com.firefly.core.lending.factoring.interfaces.dtos.fee.v1.FactoringFeeDTO;
import com.firefly.core.lending.factoring.models.entities.fee.v1.FactoringFee;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FactoringFeeMapper {
    FactoringFeeDTO toDTO(FactoringFee entity);
    FactoringFee toEntity(FactoringFeeDTO dto);
}