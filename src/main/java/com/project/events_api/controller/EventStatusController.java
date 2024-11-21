package com.project.events_api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.events_api.dto.EventStatusDTO;
import com.project.events_api.service.EventStatusService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Event Statuses", description = "API de status de eventos")
@RestController
@RequestMapping("/event-statuses")
public class EventStatusController {

    private final EventStatusService eventStatusService;

    public EventStatusController(EventStatusService eventStatusService) {
        this.eventStatusService = eventStatusService;
    }

    @Operation(summary = "Cria um novo status de evento", description = "Cria um novo status de evento na base de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Status de evento criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @PostMapping
    public ResponseEntity<EventStatusDTO> create(@Valid @RequestBody EventStatusDTO eventStatusDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(eventStatusService.create(eventStatusDTO));
    }

    @Operation(summary = "Retorna todos os status de eventos", description = "Retorna todos os status de eventos cadastrados na base de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Status de eventos retornados com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @GetMapping
    public ResponseEntity<List<EventStatusDTO>> findAll() {
        return ResponseEntity.ok(eventStatusService.findAll());
    }

    @Operation(summary = "Retorna um status de evento por ID", description = "Retorna um status de evento cadastrado na base de dados por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Status de evento retornado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Status de evento não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @GetMapping("/{id}")
    public ResponseEntity<EventStatusDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(eventStatusService.findById(id));
    }

    @Operation(summary = "Atualiza um status de evento", description = "Atualiza um status de evento na base de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Status de evento atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "404", description = "Status de evento não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @PutMapping("/{id}")
    public ResponseEntity<EventStatusDTO> update(@PathVariable Long id,
            @Valid @RequestBody EventStatusDTO eventStatusDTO) {
        return ResponseEntity.ok(eventStatusService.update(id, eventStatusDTO));
    }

    @Operation(summary = "Deleta um status de evento", description = "Deleta um status de evento da base de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Status de evento deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Status de evento não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        eventStatusService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
