package ru.practicum.ewm_ms.mappers;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import ru.practicum.ewm_ms.client.EventClient;
import ru.practicum.ewm_ms.dto.compilation.CompilationPostDto;
import ru.practicum.ewm_ms.dto.compilation.CompilationResponseDto;
import ru.practicum.ewm_ms.dto.event.EventShortDto;
import ru.practicum.ewm_ms.model.Compilation;
import ru.practicum.ewm_ms.model.Event;
import ru.practicum.ewm_ms.repository.EventRepository;
import ru.practicum.ewm_ms.repository.ParticipationRepository;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CompilationMapper {

    public static Compilation toModel(CompilationPostDto dto, EventRepository repo) {
        return Compilation.builder()
                .events(replaceIdWithEvents(dto.getEvents(), repo))
                .pinned(dto.getPinned())
                .title(dto.getTitle())
                .build();
    }

    public static CompilationResponseDto toResponseDto(Compilation compilation,
                                                       EventClient client,
                                                       ParticipationRepository repo) {
        return CompilationResponseDto.builder()
                .events(replaceEventsWithEventShortDto(compilation.getEvents(), client, repo))
                .id(compilation.getId())
                .pinned(compilation.getPinned())
                .title(compilation.getTitle())
                .build();
    }

    private static List<Event> replaceIdWithEvents(List<Long> ids, EventRepository repo) {
        return repo.findAll(ids);
    }

    private static List<EventShortDto> replaceEventsWithEventShortDto(List<Event> events,
                                                                      EventClient client,
                                                                      ParticipationRepository repo) {
        List<EventShortDto> dtos = new ArrayList<>();
        for (Event ev : events) {
            dtos.add(EventMapper.toEventShortDto(ev, client, repo));
        }
        return dtos;
    }

    private static String mapToString(List<Long> ids) {
        StringBuilder builder = new StringBuilder();
        for (Long id : ids) {
            builder.append(id);
            builder.append(",");
        }
        builder.deleteCharAt(builder.length() - 1);
        return builder.toString();
    }
}