package ru.practicum.ewm_ms.mappers;

import ru.practicum.ewm_ms.dto.ParticipationDto;
import ru.practicum.ewm_ms.exception.EnumParseException;
import ru.practicum.ewm_ms.exception.NotFoundException;
import ru.practicum.ewm_ms.model.ApplicationState;
import ru.practicum.ewm_ms.model.Event;
import ru.practicum.ewm_ms.model.Participation;
import ru.practicum.ewm_ms.model.User;
import ru.practicum.ewm_ms.repository.EventRepository;
import ru.practicum.ewm_ms.repository.UserRepository;
import ru.practicum.ewm_ms.util.NotFoundMessageGen;

public class ParticipationMapper {

    private ParticipationMapper() {}

    public static Participation toModel(ParticipationDto dto, EventRepository eventRepo, UserRepository userRepo) {
        return Participation.builder()
                .created(DateTimeMapper.toDateTime(dto.getCreated()))
                .event(getEventById(dto.getEvent(), eventRepo))
                .id(dto.getId())
                .requester(getUserById(dto.getRequester(), userRepo))
                .state(parseApplicationState(dto.getState()))
                .build();
    }

    public static ParticipationDto toDto(Participation model) {
        return ParticipationDto.builder()
                .created(DateTimeMapper.toString(model.getCreated()))
                .event(model.getEvent().getId())
                .id(model.getId())
                .requester(model.getRequester().getId())
                .state(model.getState().toString())
                .build();
    }

    private static ApplicationState parseApplicationState(String state) {
        ApplicationState enumState;
        try{
            enumState = ApplicationState.valueOf(state.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new EnumParseException("Недопустимое значение статуса заявки" + state);
        }
        return enumState;
    }

    private static Event getEventById(Long eventId, EventRepository repo) {
        Event event = repo.findById(eventId).orElse(null);
        if (event == null){
            throw new NotFoundException(NotFoundMessageGen.getEventNotFoundMessage(eventId));
        }
        return event;
    }

    private static User getUserById(Long userId, UserRepository repo) {
        User user = repo.findById(userId).orElse(null);
        if (user == null){
            throw new NotFoundException(NotFoundMessageGen.getUserNotFoundMessage(userId));
        }
        return user;
    }
}