package ru.practicum.ewm_ms.service;

import ru.practicum.ewm_ms.dto.ParticipationDto;
import ru.practicum.ewm_ms.dto.event.EventDetailedDto;
import ru.practicum.ewm_ms.dto.event.EventPatchDto;
import ru.practicum.ewm_ms.dto.event.EventPostDto;
import ru.practicum.ewm_ms.dto.event.EventShortDto;

import java.util.List;

public interface EventService {
    List<EventShortDto> getEvents(
        String text,
        Integer[] categories,
        Boolean paid,
        String rangeStart,
        String rangeEnd,
        Boolean onlyAvailable,
        String sort,
        Integer from,
        Integer size
    ) ;

    EventDetailedDto findEventById(Long id);

    List<EventShortDto> findEventsByUserId(Long userId, Integer from, Integer size);

    EventDetailedDto patchEvent(Long userId, EventPatchDto dto);

    EventDetailedDto postEvent(Long userId, EventPostDto dto);

    EventDetailedDto findEventByIdAndOwnerId(Long userId, Long eventId);

    EventDetailedDto cancelEventByIdAndOwnerId(Long userId, Long eventId);

    ParticipationDto getInfoAboutEventParticipation(Long userId, Long eventId);

    ParticipationDto confirmParticipation(Long userId, Long eventId, Long reqId);

    ParticipationDto rejectParticipation(Long userId, Long eventId, Long reqId);

    List<EventDetailedDto> findEventsByConditions(Long[] userIds,
                                                  String[] states,
                                                  Integer[] categories,
                                                  String rangeStart,
                                                  String rangeEnd,
                                                  Integer from,
                                                  Integer size);

    EventDetailedDto editEvent(Long eventId, EventPostDto dto);

    EventDetailedDto publishEvent(Long eventId);

    EventDetailedDto rejectEvent(Long eventId);
}