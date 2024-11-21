package com.project.events_api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.events_api.dto.EventDTO;
import com.project.events_api.mapper.EventMapper;
import com.project.events_api.model.Event;
import com.project.events_api.repository.EventRepository;
import com.project.events_api.util.ResourceNotFoundException;

/**
 * Classe de serviço para a entidade Event
 * CRUD de eventos
 * 
 * @author Gabrielly Freire
 * @since 1.0
 */
@Service
public class EventService {

    private final EventRepository eventRepository;
    private final EventMapper eventMapper;

    @Autowired
    public EventService(EventRepository eventRepository, EventMapper eventMapper) {
        this.eventRepository = eventRepository;
        this.eventMapper = eventMapper;
    }

    /**
     * Cria um novo evento
     * 
     * @param eventDTO - DTO do evento
     * @return DTO do evento criado
     */
    public EventDTO create(EventDTO eventDTO) {
        Event event = eventMapper.toModel(eventDTO);
        event = eventRepository.save(event);
        return eventMapper.toDTO(event);
    }

    /**
     * Retorna todos os eventos
     * 
     * @return Lista de DTOs de eventos
     */
    public List<EventDTO> findAll() {
        return eventRepository.findAll().stream()
                .map(eventMapper::toDTO)
                .toList();
    }

    /**
     * Retorna um evento por ID
     * 
     * @param id - ID do evento
     * @return DTO do evento
     */
    public EventDTO findById(Long id) {
        Event event = validateExistsEvent(id);
        return eventMapper.toDTO(event);
    }

    /**
     * Atualiza um evento existente
     * 
     * @param id       - ID do evento a ser atualizado
     * @param eventDTO - DTO com os novos dados do evento
     * @return DTO atualizado do evento
     */
    public EventDTO update(Long id, EventDTO eventDTO) {
        Event oldEvent = validateExistsEvent(id);
        Event newEvent = eventMapper.toModel(eventDTO);

        oldEvent = helperUpdate(oldEvent, newEvent);

        return eventMapper.toDTO(eventRepository.save(oldEvent));
    }

    /**
     * Deleta um evento por ID
     * 
     * @param id - ID do evento a ser deletado
     */
    public void delete(Long id) {
        validateExistsEvent(id);
        eventRepository.deleteById(id);
    }

    /**
     * Valida se o evento existe pelo ID
     * 
     * @param id - ID do evento a ser verificado
     * @return Evento encontrado
     */
    private Event validateExistsEvent(Long id) {
        return eventRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Evento não encontrado com o ID: " + id));
    }

    private Event helperUpdate(Event oldEvent, Event newEvent) {
        oldEvent.setName(newEvent.getName());
        oldEvent.setDescription(newEvent.getDescription());
        oldEvent.setAddress(newEvent.getAddress());
        oldEvent.setLinkMaps(newEvent.getLinkMaps());
        oldEvent.setStatus(newEvent.getStatus());
        oldEvent.setType(newEvent.getType());
        oldEvent.setCategory(newEvent.getCategory());
        oldEvent.setStartDate(newEvent.getStartDate());
        oldEvent.setEndDate(newEvent.getEndDate());
        oldEvent.setLink(newEvent.getLink());
        oldEvent.setParticipantsNumber(newEvent.getParticipantsNumber());
        oldEvent.setBannerId(newEvent.getBannerId());
        return oldEvent;
    }
}
