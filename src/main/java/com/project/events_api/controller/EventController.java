package com.project.events_api.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.events_api.dto.EventDTO;
import com.project.events_api.service.EventService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Events", description = "API de eventos")
@RestController
@RequestMapping("/events")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @Operation(summary = "Cria um evento", description = "Cria um evento com as informações fornecidas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Evento criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @PostMapping
    public ResponseEntity<EventDTO> createEvent(@Valid @RequestBody EventDTO eventDTO) {
        return ResponseEntity.ok(eventService.create(eventDTO));
    }

    @Operation(summary = "Retorna todos os eventos", description = "Retorna todos os eventos cadastrados na base de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Eventos retornados com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @GetMapping
    public ResponseEntity<List<EventDTO>> findAll() {
        return ResponseEntity.ok(eventService.findAll());
    }

    @Operation(summary = "Retorna um evento por ID", description = "Retorna um evento cadastrado na base de dados por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Evento retornado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Evento não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @GetMapping("/{id}")
    public ResponseEntity<EventDTO> findById(Long id) {
        return ResponseEntity.ok(eventService.findById(id));
    }

    @Operation(summary = "Atualiza um evento", description = "Atualiza um evento com as informações fornecidas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Evento atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @PutMapping("/{id}")
    public ResponseEntity<EventDTO> updateEvent(@PathVariable Long id, @Valid @RequestBody EventDTO eventDTO) {
        return ResponseEntity.ok(eventService.update(id, eventDTO));
    }

    @Operation(summary = "Deleta um evento", description = "Deleta um evento cadastrado na base de dados por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Evento deletado com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(Long id) {
        eventService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
