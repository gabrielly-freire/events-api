package com.project.events_api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.events_api.dto.EventStatusDTO;
import com.project.events_api.mapper.EventStatusMapper;
import com.project.events_api.model.EventStatus;
import com.project.events_api.repository.EventStatusRepository;
import com.project.events_api.util.ResourceNotFoundException;

/**
 * Classe de serviço para a entidade Event Status
 * CRUD de status de eventos
 * 
 * @author Gabrielly Freire
 * @since 1.0
 */
@Service
public class EventStatusService {

    private final EventStatusRepository eventStatusRepository;
    private final EventStatusMapper eventStatusMapper;

    @Autowired
    public EventStatusService(EventStatusRepository eventStatusRepository, EventStatusMapper eventStatusMapper) {
        this.eventStatusRepository = eventStatusRepository;
        this.eventStatusMapper = eventStatusMapper;
    }

    /**
     * Cria um novo status de evento
     * 
     * @param eventStatusDTO - DTO do status do evento
     * @return DTO do status do evento criado
     */
    public EventStatusDTO create(EventStatusDTO eventStatusDTO) {
        EventStatus eventStatus = eventStatusMapper.toModel(eventStatusDTO);
        eventStatus = eventStatusRepository.save(eventStatus);
        return eventStatusMapper.toDTO(eventStatus);
    }

    /**
     * Retorna todos os status de eventos
     * 
     * @return Lista de DTOs de status de eventos
     */
    public List<EventStatusDTO> findAll() {
        return eventStatusRepository.findAll().stream()
                .map(eventStatusMapper::toDTO)
                .toList();
    }

    /**
     * Retorna um status de evento por ID
     * 
     * @param id - ID do status do evento
     * @return DTO do status do evento
     */
    public EventStatusDTO findById(Long id) {
        EventStatus eventStatus = validateExistsEvent(id);
        return eventStatusMapper.toDTO(eventStatus);
    }

    /**
     * Atualiza um status de evento existente
     * 
     * @param id             - ID do status do evento a ser atualizado
     * @param eventStatusDTO - DTO com os novos dados do status do evento
     * @return DTO atualizado do status do evento
     */
    public EventStatusDTO update(Long id, EventStatusDTO eventStatusDTO) {
        EventStatus oldEventStatus = validateExistsEvent(id);
        EventStatus newEventStatus = eventStatusMapper.toModel(eventStatusDTO);

        oldEventStatus = helperUpdate(oldEventStatus, newEventStatus);

        return eventStatusMapper.toDTO(eventStatusRepository.save(oldEventStatus));
    }

    /**
     * Deleta um status de evento por ID
     * 
     * @param id - ID do status do evento a ser deletado
     */
    public void delete(Long id) {
        validateExistsEvent(id);
        eventStatusRepository.deleteById(id);
    }

    /**
     * Valida se o status do evento existe pelo ID
     * 
     * @param id - ID do status do evento a ser verificado
     * @return Status encontrado
     */
    private EventStatus validateExistsEvent(Long id) {
        return eventStatusRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Evento não encontrado com o ID: " + id));
    }

    /**
     * Atualiza os dados do status do evento
     * 
     * @param oldEventStatus    - Status do evento a ser atualizado
     * @param newEventStatusDTO - DTO com os novos dados do status do evento
     * @return Status do evento atualizado
     */
    private EventStatus helperUpdate(EventStatus oldEventStatus, EventStatus newEventStatusDTO) {
        oldEventStatus.setName(newEventStatusDTO.getName());
        oldEventStatus.setDescription(newEventStatusDTO.getDescription());
        return oldEventStatus;
    }
}
