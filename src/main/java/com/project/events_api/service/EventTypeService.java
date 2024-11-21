package com.project.events_api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.events_api.dto.EventTypeDTO;
import com.project.events_api.mapper.EventTypeMapper;
import com.project.events_api.model.EventType;
import com.project.events_api.repository.EventTypeRepository;
import com.project.events_api.util.ResourceNotFoundException;

/**
 * Classe de serviço para a entidade Event Type
 * CRUD de tipos de eventos
 * 
 * @author Gabrielly Freire
 * @since 1.0
 */
@Service
public class EventTypeService {

    private final EventTypeRepository eventTypeRepository;
    private final EventTypeMapper eventTypeMapper;

    @Autowired
    public EventTypeService(EventTypeRepository eventTypeRepository, EventTypeMapper eventTypeMapper) {
        this.eventTypeRepository = eventTypeRepository;
        this.eventTypeMapper = eventTypeMapper;
    }

    /**
     * Cria um novo tipo de evento
     * 
     * @param eventTypeDTO - DTO do tipo de evento
     * @return DTO do tipo de evento criado
     */
    public EventTypeDTO create(EventTypeDTO eventTypeDTO) {
        EventType eventType = eventTypeMapper.toModel(eventTypeDTO);
        eventType = eventTypeRepository.save(eventType);
        return eventTypeMapper.toDTO(eventType);
    }

    /**
     * Retorna todos os tipos de eventos
     * 
     * @return Lista de DTOs de tipos de eventos
     */
    public List<EventTypeDTO> findAll() {
        return eventTypeRepository.findAll().stream()
                .map(eventTypeMapper::toDTO)
                .toList();
    }

    /**
     * Retorna um tipo de evento por ID
     * 
     * @param id - ID do tipo de evento
     * @return DTO do tipo de evento
     */
    public EventTypeDTO findById(Long id) {
        EventType eventType = validateExistsEvent(id);
        return eventTypeMapper.toDTO(eventType);
    }

    /**
     * Atualiza um tipo de evento existente
     * 
     * @param id           - ID do tipo de evento a ser atualizado
     * @param eventTypeDTO - DTO com os novos dados do tipo de evento
     * @return DTO atualizado do tipo de evento
     */
    public EventTypeDTO update(Long id, EventTypeDTO eventTypeDTO) {
        EventType oldEventType = validateExistsEvent(id);
        EventType newEventType = eventTypeMapper.toModel(eventTypeDTO);

        oldEventType = helperUpdate(oldEventType, newEventType);

        return eventTypeMapper.toDTO(eventTypeRepository.save(oldEventType));
    }

    /**
     * Deleta um tipo de evento por ID
     * 
     * @param id - ID do tipo de evento a ser deletado
     */
    public void delete(Long id) {
        validateExistsEvent(id);
        eventTypeRepository.deleteById(id);
    }

    /**
     * Valida se o tipo de evento existe pelo ID
     * 
     * @param id - ID do tipo de evento a ser verificado
     * @return Tipo encontrado
     */
    private EventType validateExistsEvent(Long id) {
        return eventTypeRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Tipo de evento não encontrado com o ID: " + id));
    }

    /**
     * Atualiza os dados do tipo de evento
     * 
     * @param oldEventType - Tipo de evento antigo
     * @param newEventType - Tipo de evento novo
     * @return Tipo de evento atualizado
     */
    private EventType helperUpdate(EventType oldEventType, EventType newEventType) {
        oldEventType.setName(newEventType.getName());
        oldEventType.setDescription(newEventType.getDescription());
        return oldEventType;
    }
}
