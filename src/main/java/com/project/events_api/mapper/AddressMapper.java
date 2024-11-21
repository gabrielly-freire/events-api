package com.project.events_api.mapper;

import org.springframework.stereotype.Component;

import com.project.events_api.dto.AddressDTO;
import com.project.events_api.model.Address;

@Component
public class AddressMapper {
    
    public Address toModel(AddressDTO dto){
        if(dto == null){
            return null;
        }

        Address address = Address.builder()
            .postalCode(dto.postalCode())
            .street(dto.street())
            .neighborhood(dto.neighborhood())
            .state(dto.state())
            .city(dto.city())
            .number(dto.number())
            .complement(dto.complement())
            .build();

        return address;
    }

    public AddressDTO toDTO(Address model){
        if(model == null){
            return null;
        }

        AddressDTO dto = new AddressDTO(
            model.getId(),
            model.getPostalCode(),
            model.getStreet(),
            model.getNeighborhood(),
            model.getState(),
            model.getCity(),
            model.getNumber(),
            model.getComplement()
        );

        return dto;
    }
}
