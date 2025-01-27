package com.factoriaF5.cukies.DTOs.customer;

import com.factoriaF5.cukies.model.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    CustomerDTOResponse toDTOResponse(Customer customer);
    Customer toEntity(CustomerDTORequest customerDTORequest);
}
