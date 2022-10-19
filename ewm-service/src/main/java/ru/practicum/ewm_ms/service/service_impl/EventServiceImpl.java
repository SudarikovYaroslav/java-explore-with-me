package ru.practicum.ewm_ms.service.service_impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.ewm_ms.dto.ParticipationDto;
import ru.practicum.ewm_ms.dto.event.EventDetailedDto;
import ru.practicum.ewm_ms.dto.event.EventPatchDto;
import ru.practicum.ewm_ms.dto.event.EventPostDto;
import ru.practicum.ewm_ms.dto.event.EventShortDto;
import ru.practicum.ewm_ms.repository.EventRepository;
import ru.practicum.ewm_ms.service.EventService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;

    //TODO реализовать логику добавить маппер и репозиторий
    @Override
    public List<EventShortDto> getEvents(
            String text,
            Integer[] categories,
            Boolean paid,
            String rangeStart,
            String rangeEnd,
            Boolean onlyAvailable,
            String sort,
            Integer from,
            Integer size) {
        return null;
    }

    // TODO реализовать логику
    @Override
    public EventDetailedDto findEventById(Long id) {
        return null;
    }

    //TODO реализовать логику
    @Override
    public List<EventShortDto> findEventsByUserId(Long userId, Integer from, Integer size) {
        return null;
    }

    //TODO реализовать логику
    @Override
    public EventDetailedDto patchEvent(Long userId, EventPatchDto dto) {
        return null;
    }

    //TODO реализовать логику
    @Override
    public EventDetailedDto postEvent(Long userId, EventPostDto dto) {
        return null;
    }

    //TODO реализовать логику
    @Override
    public EventDetailedDto findEventByIdAndOwnerId(Long userId, Long eventId) {
        return null;
    }

    //TODO реализовать логику
    @Override
    public EventDetailedDto cancelEventByIdAndOwnerId(Long userId, Long eventId) {
        return null;
    }

    //TODO реализовать логику
    @Override
    public ParticipationDto getInfoAboutEventParticipation(Long userId, Long eventId) {
        return null;
    }

    //TODO реализовать логику
    @Override
    public ParticipationDto confirmParticipation(Long userId, Long eventId, Long reqId) {
        return null;
    }

    //TODO реализовать логику
    @Override
    public ParticipationDto rejectParticipation(Long userId, Long eventId, Long reqId) {
        return null;
    }

    //TODO реализовать логику
    @Override
    public List<EventDetailedDto> findEventsByConditions(Long[] userIds,
                                                         String[] states,
                                                         Integer[] categories,
                                                         String rangeStart,
                                                         String rangeEnd,
                                                         Integer from,
                                                         Integer size) {
        return null;
    }

    //TODO реализовать логику
    @Override
    public EventDetailedDto editEvent(Long eventId, EventPostDto dto) {
        return null;
    }

    //TODO реализовать логику
    @Override
    public EventDetailedDto publishEvent(Long eventId) {
        return null;
    }

    //TODO реализовать логику
    @Override
    public EventDetailedDto rejectEvent(Long eventId) {
        return null;
    }
}