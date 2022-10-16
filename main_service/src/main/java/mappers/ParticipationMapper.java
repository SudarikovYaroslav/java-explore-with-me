package mappers;

import dto.ParticipationDto;
import exception.EnumParseException;
import model.ApplicationState;
import model.Participation;

public class ParticipationMapper {

    private ParticipationMapper() {}

    public static Participation toModel(ParticipationDto dto) {
        return Participation.builder()
                .created(DateTimeMapper.toDateTime(dto.getCreated()))
                .event(dto.getEvent())
                .id(dto.getId())
                .requester(dto.getRequester())
                .state(parseApplicationState(dto.getState()))
                .build();
    }

    public static ParticipationDto toDto(Participation model) {
        return ParticipationDto.builder()
                .created(DateTimeMapper.toString(model.getCreated()))
                .event(model.getEvent())
                .id(model.getId())
                .requester(model.getRequester())
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
}