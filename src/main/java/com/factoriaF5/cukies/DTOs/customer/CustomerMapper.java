package com.factoriaF5.cukies.DTOs.customer;

import com.factoriaF5.cukies.model.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerMapper {
    CustomerMapper INSTANCE = Mappers.getMapper( CustomerMapper.class );

    CustomerDTOResponse toDTOResponse(Customer customer);
    Customer toEntity(CustomerDTORequest customerDTORequest);
}
