package com.project.events_api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.events_api.model.Category;
import com.project.events_api.model.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    List<Event> findByNameContaining(String name);

    List<Event> findByCategory(Category category);

    List<Event> findByPriceIsNullOrPrice(Double price);

    List<Event> findByPriceNot(Double price);

    @Query("SELECT e FROM Event e WHERE FUNCTION('DAYOFWEEK', e.startDate) IN (1, 7)")
    List<Event> findWeekendEvents();

    @Query("SELECT e FROM Event e WHERE DATE(e.startDate) = CURRENT_DATE")
    List<Event> findEventsToday();

}
