package ru.practicum.ewm_ms.mappers;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import ru.practicum.ewm_ms.dto.compilation.CompilationPostDto;
import ru.practicum.ewm_ms.dto.compilation.CompilationResponseDto;
import ru.practicum.ewm_ms.dto.event.EventShortDto;
import ru.practicum.ewm_ms.model.Compilation;
import ru.practicum.ewm_ms.model.Event;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CompilationMapper {

    public static Compilation toModel(CompilationPostDto dto, List<Event> events) {
        return Compilation.builder()
                .events(events)
                .pinned(dto.getPinned())
                .title(dto.getTitle())
                .build();
    }

    // TODO разобраться с replaceEventsWithEventShortDto. Сначала сделать EventMapper
    public static CompilationResponseDto toResponseDto(Compilation compilation, List<EventShortDto> eventsDto) {
        return CompilationResponseDto.builder()
                .events(eventsDto)
                .id(compilation.getId())
                .pinned(compilation.getPinned())
                .title(compilation.getTitle())
                .build();
    }

//    private static List<EventShortDto> replaceEventsWithEventShortDto(List<Event> events,
//                                                                      EventClient client,
//                                                                      ParticipationRepository repo) {
//        List<EventShortDto> dtos = new ArrayList<>();
//        for (Event ev : events) {
//            dtos.add(EventMapper.toEventShortDto(ev, client, repo));
//        }
//        return dtos;
//    }
//    private static List<EventShortDto> replaceEventsWithEventShortDto(List<Event> events,
//                                                                  EventClient client,
//                                                                  ParticipationRepository repo) {
//    List<EventShortDto> dtos = new ArrayList<>();
//    for (Event ev : events) {
//        dtos.add(EventMapper.toEventShortDto(ev, client, repo));
//    }
//    return dtos;
//    }
}