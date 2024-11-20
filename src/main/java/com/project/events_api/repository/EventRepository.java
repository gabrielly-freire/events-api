package com.project.events_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.events_api.model.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

} 
