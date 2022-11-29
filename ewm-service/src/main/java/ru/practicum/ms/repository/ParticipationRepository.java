package ru.practicum.ms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.practicum.ms.client.dto.UtilDto;
import ru.practicum.ms.model.Participation;
import ru.practicum.ms.model.ParticipationState;

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

    // TODO мегакрутой запрос
    @Query("select new ru.practicum.ms.client.dto.UtilDto(p.event.id, count(p)) " +
            "from participations as p where p.event.id in ?1 and p.state = ?2 group by p.event.id")
    List<UtilDto> countParticipationByEventIds(List<Long> eventIds, ParticipationState state);
}
