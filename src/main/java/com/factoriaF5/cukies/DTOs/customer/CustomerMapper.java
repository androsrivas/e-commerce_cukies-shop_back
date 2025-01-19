package com.factoriaF5.cukies.DTOs.customer;

import com.factoriaF5.cukies.model.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    CustomerDTOResponse toDTOResponse(Customer customer);
    Customer toEntity(CustomerDTORequest customerDTORequest);
}
