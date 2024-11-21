package com.project.events_api.mapper;

import org.springframework.stereotype.Component;

import com.project.events_api.dto.PersonDTO;
import com.project.events_api.model.Person;

@Component
public class PersonMapper {

    private final AddressMapper addressMapper;

    public PersonMapper(AddressMapper addressMapper) {
        this.addressMapper = addressMapper;
    }

    public Person toModel(PersonDTO dto) {
        Person person = Person.builder()
                .id(dto.id())
                .name(dto.name())
                .cpf(dto.cpf())
                .email(dto.email())
                .phone(dto.phone())
                .birthDate(dto.birthDate())
                .address(
                        addressMapper.toModel(dto.address()))
                .build();

        return person;
    }

    public PersonDTO toDTO(Person model) {
        PersonDTO dto = new PersonDTO(
                model.getId(),
                model.getName(),
                model.getCpf(),
                model.getEmail(),
                model.getPhone(),
                model.getBirthDate(),
                addressMapper.toDTO(model.getAddress()));

        return dto;
    }
}
