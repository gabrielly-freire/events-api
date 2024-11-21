package com.project.events_api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.events_api.dto.EventTypeDTO;
import com.project.events_api.service.EventTypeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Event Types", description = "API de tipos de eventos")
@RestController
@RequestMapping("/event-types")
public class EventTypeController {

    private final EventTypeService eventTypeService;

    public EventTypeController(EventTypeService eventTypeService) {
        this.eventTypeService = eventTypeService;
    }

    @Operation(summary = "Cria um novo tipo de evento", description = "Cria um novo tipo de evento na base de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Tipo de evento criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @PostMapping
    public ResponseEntity<EventTypeDTO> create(@Valid @RequestBody EventTypeDTO eventTypeDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(eventTypeService.create(eventTypeDTO));
    }

    @Operation(summary = "Retorna todos os tipos de eventos", description = "Retorna todos os tipos de eventos cadastrados na base de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tipos de eventos retornados com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @GetMapping
    public ResponseEntity<List<EventTypeDTO>> findAll() {
        return ResponseEntity.ok(eventTypeService.findAll());
    }

    @Operation(summary = "Retorna um tipo de evento por ID", description = "Retorna um tipo de evento cadastrado na base de dados por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tipo de evento retornado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Tipo de evento não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @GetMapping("/{id}")
    public ResponseEntity<EventTypeDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(eventTypeService.findById(id));
    }

    @Operation(summary = "Atualiza um tipo de evento", description = "Atualiza um tipo de evento na base de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tipo de evento atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "404", description = "Tipo de evento não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @PutMapping("/{id}")
    public ResponseEntity<EventTypeDTO> update(@PathVariable Long id, @Valid @RequestBody EventTypeDTO eventTypeDTO) {
        return ResponseEntity.ok(eventTypeService.update(id, eventTypeDTO));
    }

    @Operation(summary = "Deleta um tipo de evento", description = "Deleta um tipo de evento na base de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Tipo de evento deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Tipo de evento não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        eventTypeService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
