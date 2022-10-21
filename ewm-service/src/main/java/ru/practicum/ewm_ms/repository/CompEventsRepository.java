package ru.practicum.ewm_ms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.practicum.ewm_ms.model.CompEvent;

public interface CompEventsRepository extends JpaRepository<CompEvent, Long> {
    void deleteByCompilationIdAndEventId(long compilationId, long eventId);
}
