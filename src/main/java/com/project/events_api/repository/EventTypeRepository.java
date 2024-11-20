package com.project.events_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.events_api.model.EventType;

@Repository
public interface EventTypeRepository extends JpaRepository<EventType, Long> {
    
}
