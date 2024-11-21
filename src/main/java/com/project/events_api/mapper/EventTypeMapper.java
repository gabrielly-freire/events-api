package com.project.events_api.mapper;

import org.springframework.stereotype.Component;

import com.project.events_api.dto.EventTypeDTO;
import com.project.events_api.model.EventType;

@Component
public class EventTypeMapper {
    
    public EventType toModel(EventTypeDTO dto){
        EventType eventType = EventType.builder()
            .name(dto.name())
            .description(dto.description())
            .build();

        return eventType;
    }

    public EventTypeDTO toDTO(EventType model){
        EventTypeDTO dto = new EventTypeDTO(
            model.getId(),
            model.getName(),
            model.getDescription()
        );

        return dto;
    }
}
