package com.firefly.core.lending.factoring.core.mappers.invoice.v1;

import com.firefly.core.lending.factoring.interfaces.dtos.invoice.v1.FactoringInvoiceStatusHistoryDTO;
import com.firefly.core.lending.factoring.models.entities.invoice.v1.FactoringInvoiceStatusHistory;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FactoringInvoiceStatusHistoryMapper {
    FactoringInvoiceStatusHistoryDTO toDTO(FactoringInvoiceStatusHistory entity);
    FactoringInvoiceStatusHistory toEntity(FactoringInvoiceStatusHistoryDTO dto);
}