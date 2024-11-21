package com.project.events_api.mapper;

import org.springframework.stereotype.Component;

import com.project.events_api.dto.EventStatusDTO;
import com.project.events_api.model.EventStatus;

@Component
public class EventStatusMapper {
    
    public EventStatus toModel(EventStatusDTO dto){
        EventStatus eventStatus = EventStatus.builder()
            .name(dto.name())
            .description(dto.description())
            .build();

        return eventStatus;
    }

    public EventStatusDTO toDTO(EventStatus model){
        EventStatusDTO dto = new EventStatusDTO(
            model.getId(),
            model.getName(),
            model.getDescription()
        );

        return dto;
    }
}
