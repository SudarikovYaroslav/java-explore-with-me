package main_service_application.service.service_impl;

import main_service_application.dto.ParticipationDto;
import main_service_application.dto.event.EventDetailedDto;
import main_service_application.dto.event.EventPatchDto;
import main_service_application.dto.event.EventPostDto;
import main_service_application.dto.event.EventShortDto;
import lombok.RequiredArgsConstructor;
import main_service_application.repository.EventRepository;
import org.springframework.stereotype.Service;
import main_service_application.service.EventService;

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
