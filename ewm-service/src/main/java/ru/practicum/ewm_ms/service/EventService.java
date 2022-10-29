package ru.practicum.ewm_ms.service;

import ru.practicum.ewm_ms.dto.ParticipationDto;
import ru.practicum.ewm_ms.dto.event.EventDetailedDto;
import ru.practicum.ewm_ms.dto.event.EventPatchDto;
import ru.practicum.ewm_ms.dto.event.EventPostDto;
import ru.practicum.ewm_ms.dto.event.EventShortDto;
import ru.practicum.ewm_ms.model.EventSearchParams;

import java.util.List;

public interface EventService {
    List<EventShortDto> getEvents(EventSearchParams criteria, String clientIp, String endpoint);

    EventDetailedDto findEventById(Long id, String clientIp, String endpoint);

    List<EventShortDto> findEventsByInitiatorId(Long userId, Integer from, Integer size);

    EventDetailedDto patchEvent(Long userId, EventPatchDto dto);

    EventDetailedDto postEvent(Long userId, EventPostDto dto);

    EventDetailedDto findEventByIdAndOwnerId(Long userId, Long eventId);

    EventDetailedDto cancelEventByIdAndOwnerId(Long userId, Long eventId);

    List<ParticipationDto> getInfoAboutEventParticipation(Long userId, Long eventId);

    ParticipationDto confirmParticipation(Long userId, Long eventId, Long reqId);

    ParticipationDto rejectParticipation(Long userId, Long eventId, Long reqId);

    List<EventDetailedDto> findEventsByConditions(EventSearchParams params);

    EventDetailedDto editEvent(Long eventId, EventPostDto dto);

    EventDetailedDto publishEvent(Long eventId);

    EventDetailedDto rejectEvent(Long eventId);
}