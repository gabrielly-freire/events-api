package com.project.events_api.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.events_api.model.Category;
import com.project.events_api.model.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    List<Event> findByNameContaining(String name);

    List<Event> findByCategory(Category category);

    List<Event> findByPriceIsNullOrPrice(Double price);

    List<Event> findByPriceNot(Double price);

    @Query(value = "SELECT * FROM events e WHERE DATE(e.start_date) = DATE(:startOfWeek) OR DATE(e.start_date) = DATE(:endOfWeek)", nativeQuery = true)
    List<Event> findWeekendEvents(@Param("startOfWeek") Date startOfWeek,
            @Param("endOfWeek") Date endOfWeek);

    @Query("SELECT e FROM Event e WHERE DATE(e.startDate) = CURRENT_DATE")
    List<Event> findEventsToday();

    @Query("SELECT e FROM Event e ORDER BY e.startDate")
    List<Event> findAll();

}
