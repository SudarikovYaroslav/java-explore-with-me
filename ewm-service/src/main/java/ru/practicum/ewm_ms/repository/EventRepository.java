package ru.practicum.ewm_ms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.practicum.ewm_ms.model.Event;

public interface EventRepository extends JpaRepository<Event, Long> {
}
