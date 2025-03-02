package com.catalis.core.lending.factoring.core.mappers.invoice.v1;

import com.catalis.core.lending.factoring.interfaces.dtos.invoice.v1.FactoringInvoiceDTO;
import com.catalis.core.lending.factoring.models.entities.invoice.v1.FactoringInvoice;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FactoringInvoiceMapper {
    FactoringInvoiceDTO toDTO(FactoringInvoice entity);
    FactoringInvoice toEntity(FactoringInvoiceDTO dto);
}