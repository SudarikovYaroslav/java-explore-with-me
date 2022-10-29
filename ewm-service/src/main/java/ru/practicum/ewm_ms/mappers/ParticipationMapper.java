package ru.practicum.ewm_ms.mappers;

import ru.practicum.ewm_ms.dto.ParticipationDto;
import ru.practicum.ewm_ms.exception.EnumParseException;
import ru.practicum.ewm_ms.exception.NotFoundException;
import ru.practicum.ewm_ms.model.Event;
import ru.practicum.ewm_ms.model.Participation;
import ru.practicum.ewm_ms.model.ParticipationState;
import ru.practicum.ewm_ms.model.User;
import ru.practicum.ewm_ms.repository.EventRepository;
import ru.practicum.ewm_ms.repository.UserRepository;
import ru.practicum.ewm_ms.util.Util;

public class ParticipationMapper {

    private ParticipationMapper() {
    }

    public static Participation toModel(ParticipationDto dto, EventRepository eventRepo, UserRepository userRepo) {
        return Participation.builder()
                .created(DateTimeMapper.toDateTime(dto.getCreated()))
                .event(getEventById(dto.getEvent(), eventRepo))
                .id(dto.getId())
                .requester(getUserById(dto.getRequester(), userRepo))
                .state(parseApplicationState(dto.getStatus()))
                .build();
    }

    public static ParticipationDto toDto(Participation model) {
        return ParticipationDto.builder()
                .created(DateTimeMapper.toString(model.getCreated()))
                .event(model.getEvent().getId())
                .id(model.getId())
                .requester(model.getRequester().getId())
                .status(model.getState().toString())
                .build();
    }

    private static ParticipationState parseApplicationState(String state) {
        ParticipationState enumState;
        try {
            enumState = ParticipationState.valueOf(state.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new EnumParseException("Недопустимое значение статуса заявки" + state);
        }
        return enumState;
    }

    private static Event getEventById(Long eventId, EventRepository repo) {
        Event event = repo.findById(eventId).orElse(null);
        if (event == null) {
            throw new NotFoundException(Util.getEventNotFoundMessage(eventId));
        }
        return event;
    }

    private static User getUserById(Long userId, UserRepository repo) {
        User user = repo.findById(userId).orElse(null);
        if (user == null) {
            throw new NotFoundException(Util.getUserNotFoundMessage(userId));
        }
        return user;
    }
}