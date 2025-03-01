package com.project.events_api.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.SQLRestriction;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Classe que representa um evento.
 * @author Gabrielly Freire
 * @since 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "events")
@SQLRestriction("active = true")
public class Event extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name; 

    @Column(name = "description", columnDefinition = "TEXT")
    private String description; 

    @Column(name = "address_id")
    private Address address; 

    @Column(name = "link_maps")
    private String linkMaps; 

    @ManyToOne
    private EventStatus status; 

    @ManyToOne
    private EventType type; 

    @ManyToOne
    private Category category; 

    @Column(name = "start_date", nullable = false)
    private LocalDateTime startDate; 

    @Column(name = "end_date", nullable = false)
    private LocalDateTime endDate; 

    @Column(name = "link")
    private String link; 

    @Column(name = "participants_number")
    private Integer participantsNumber; 
    
    private String banner; 

    private Double price;
}
