package ru.practicum.ewm_ms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.practicum.ewm_ms.model.Participation;

import java.util.List;
import java.util.Optional;

public interface ParticipationRepository extends JpaRepository<Participation, Long> {
    List<Participation> findAllByRequesterId(Long requesterId);

    Optional<Participation> findByEventIdAndRequesterId(Long eventId, Long requesterId);
}
