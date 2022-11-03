package ru.practicum.ms.service;

import ru.practicum.ms.dto.ParticipationDto;

import java.util.List;

public interface ParticipationService {
    List<ParticipationDto> getInfoAboutAllParticipation(Long userId);

    ParticipationDto addParticipationQuery(Long userId, Long eventId);

    ParticipationDto cancelParticipation(Long userId, Long requestId);
}
