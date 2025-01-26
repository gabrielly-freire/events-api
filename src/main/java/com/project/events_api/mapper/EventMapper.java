package com.project.events_api.mapper;

import org.springframework.stereotype.Component;

import com.project.events_api.dto.EventDTO;
import com.project.events_api.model.Event;

@Component
public class EventMapper {

    private final AddressMapper addressMapper;
    private final CategoryMapper categoryMapper;
    private final EventStatusMapper eventStatusMapper;
    private final EventTypeMapper eventTypeMapper;

    public EventMapper(AddressMapper addressMapper, CategoryMapper categoryMapper, EventStatusMapper eventStatusMapper,
            EventTypeMapper eventTypeMapper) {
        this.addressMapper = addressMapper;
        this.categoryMapper = categoryMapper;
        this.eventStatusMapper = eventStatusMapper;
        this.eventTypeMapper = eventTypeMapper;
    }

    public Event toModel(EventDTO dto) {
        Event event = Event.builder()
                .id(dto.id())
                .name(dto.name())
                .description(dto.description())
                .startDate(dto.startDate())
                .endDate(dto.endDate())
                .address(
                        addressMapper.toModel(dto.address()))
                .category(
                        categoryMapper.toModel(dto.category()))
                .status(
                        eventStatusMapper.toModel(dto.status()))
                .type(
                        eventTypeMapper.toModel(dto.type()))
                .linkMaps(dto.linkMaps())
                .link(dto.link())
                .participantsNumber(dto.participantsNumber())
                .banner(dto.banner())
                .price(dto.price())
                .build();

        return event;
    }

    public EventDTO toDTO(Event model) {
        EventDTO dto = new EventDTO(
                model.getId(),
                model.getName(),
                model.getDescription(),
                addressMapper.toDTO(model.getAddress()),
                model.getLinkMaps(),
                eventStatusMapper.toDTO(model.getStatus()),
                eventTypeMapper.toDTO(model.getType()),
                categoryMapper.toDTO(model.getCategory()),
                model.getStartDate(),
                model.getEndDate(),
                model.getLink(),
                model.getParticipantsNumber(),
                model.getBanner(),
                model.getPrice());

        return dto;
    }
}
