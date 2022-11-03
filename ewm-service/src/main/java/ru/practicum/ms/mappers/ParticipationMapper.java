package ru.practicum.ms.mappers;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import ru.practicum.ms.dto.ParticipationDto;
import ru.practicum.ms.exception.EnumParseException;
import ru.practicum.ms.model.Event;
import ru.practicum.ms.model.Participation;
import ru.practicum.ms.model.ParticipationState;
import ru.practicum.ms.model.User;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ParticipationMapper {

    public static Participation toModel(ParticipationDto dto, Event event, User requester) {
        return Participation.builder()
                .created(DateTimeMapper.toDateTime(dto.getCreated()))
                .event(event)
                .id(dto.getId())
                .requester(requester)
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
}