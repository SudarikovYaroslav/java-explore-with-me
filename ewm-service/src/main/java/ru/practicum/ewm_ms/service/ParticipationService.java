package ru.practicum.ewm_ms.service;

import ru.practicum.ewm_ms.dto.ParticipationDto;

import java.util.List;

public interface ParticipationService {
    List<ParticipationDto> getInfoAboutAllParticipation(Long userId);

    ParticipationDto addParticipationQuery(Long userId, Long eventId);

    ParticipationDto cancelParticipation(Long userId, Long requestId);
}
