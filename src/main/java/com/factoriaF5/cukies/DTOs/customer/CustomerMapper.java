package com.factoriaF5.cukies.DTOs.customer;

import com.factoriaF5.cukies.model.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    @Mapping(source = "products", target = "items")
    CustomerDTOResponse toDTOResponse(Customer customer);
    Customer toEntity(CustomerDTORequest customerDTORequest);
}
