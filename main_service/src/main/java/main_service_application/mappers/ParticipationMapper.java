package main_service_application.mappers;

import main_service_application.dto.ParticipationDto;
import main_service_application.exception.EnumParseException;
import main_service_application.exception.EventNotFoundException;
import main_service_application.exception.UserNotFoundException;
import main_service_application.model.ApplicationState;
import main_service_application.model.Event;
import main_service_application.model.Participation;
import main_service_application.model.User;
import main_service_application.repository.EventRepository;
import main_service_application.repository.UserRepository;
import main_service_application.util.NotFoundMessageGen;

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
            throw new EventNotFoundException(NotFoundMessageGen.getEventNotFoundMessage(eventId));
        }
        return event;
    }

    private static User getUserById(Long userId, UserRepository repo) {
        User user = repo.findById(userId).orElse(null);
        if (user == null){
            throw new UserNotFoundException(NotFoundMessageGen.getUserNotFoundMessage(userId));
        }
        return user;
    }
}