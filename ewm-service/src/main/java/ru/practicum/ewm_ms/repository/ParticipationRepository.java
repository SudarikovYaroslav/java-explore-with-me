package ru.practicum.ewm_ms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.practicum.ewm_ms.model.Participation;
import ru.practicum.ewm_ms.model.ParticipationState;

import java.util.List;
import java.util.Optional;

public interface ParticipationRepository extends JpaRepository<Participation, Long> {
    List<Participation> findAllByRequesterId(Long requesterId);

    Optional<Participation> findByEventIdAndRequesterId(Long eventId, Long requesterId);

    List<Participation> findAllByEventId(Long eventId);

    List<Participation> findAllByEventIdAndState(Long eventId, ParticipationState state);

    Optional<Participation> findByRequesterIdAndId(Long requesterId, Long requestId);

    @Query("select count(p) from participations as p where p.event.id = ?1 and p.state = ?2")
    int getConfirmedRequests(Long eventId, ParticipationState state);
}
