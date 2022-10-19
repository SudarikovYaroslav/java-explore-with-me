package ru.practicum.ewm_ms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.practicum.ewm_ms.model.Participation;

public interface ParticipationRepository extends JpaRepository<Participation, Long> {
}
