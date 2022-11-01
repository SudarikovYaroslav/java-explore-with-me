package ru.practicum.ewm_ms.mappers;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import ru.practicum.ewm_ms.dto.compilation.CompilationPostDto;
import ru.practicum.ewm_ms.dto.compilation.CompilationResponseDto;
import ru.practicum.ewm_ms.dto.event.EventShortDto;
import ru.practicum.ewm_ms.exception.NotFoundException;
import ru.practicum.ewm_ms.model.Compilation;
import ru.practicum.ewm_ms.model.Event;
import ru.practicum.ewm_ms.repository.EventRepository;
import ru.practicum.ewm_ms.util.Util;

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

    public static CompilationResponseDto toResponseDto(Compilation compilation) {
        return CompilationResponseDto.builder()
                .events(replaceEventsWithEventShortDto(compilation.getEvents()))
                .id(compilation.getId())
                .pinned(compilation.getPinned())
                .title(compilation.getTitle())
                .build();
    }

    private static List<Event> replaceIdWithEvents(List<Long> ids, EventRepository repo) {
        List<Event> events = new ArrayList<>();
        for (long eventId : ids) {
            Event event = repo.findById(eventId).orElse(null);
            if (event == null) {
                throw new NotFoundException(Util.getEventNotFoundMessage(eventId));
            }
            events.add(event);
        }
        return events;
    }

    private static List<EventShortDto> replaceEventsWithEventShortDto(List<Event> events) {
        List<EventShortDto> dtos = new ArrayList<>();
        for (Event ev : events) {
            dtos.add(EventMapper.toEventShortDto(ev));
        }
        return dtos;
    }
}