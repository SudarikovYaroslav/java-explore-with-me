package ru.practicum.ms.service;

import ru.practicum.ms.dto.ParticipationDto;
import ru.practicum.ms.dto.event.EventDetailedDto;
import ru.practicum.ms.dto.event.EventPatchDto;
import ru.practicum.ms.dto.event.EventPostDto;
import ru.practicum.ms.dto.event.EventShortDto;
import ru.practicum.ms.model.EventSearchParams;

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