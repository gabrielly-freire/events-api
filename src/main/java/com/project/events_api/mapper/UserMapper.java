package com.project.events_api.mapper;

import org.springframework.stereotype.Component;

import com.project.events_api.dto.UserDTO;
import com.project.events_api.model.User;

@Component
public class UserMapper {
    
    private final PersonMapper personMapper;

    public UserMapper(PersonMapper personMapper) {
        this.personMapper = personMapper;
    }

    public User toModel(UserDTO dto) {
        User user = User.builder()
                .id(dto.id())
                .username(dto.username())
                .password(dto.password())
                .person(
                        personMapper.toModel(dto.person()))
                .build();

        return user;
    }

    public UserDTO toDTO(User model) {
        UserDTO dto = new UserDTO(
                model.getId(),
                model.getUsername(),
                model.getPassword(),
                personMapper.toDTO(model.getPerson()));

        return dto;
    }
}
