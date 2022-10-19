package ru.practicum.ewm_ms.mappers;

import ru.practicum.ewm_ms.dto.compilation.CompilationPostDto;
import ru.practicum.ewm_ms.dto.compilation.CompilationResponseDto;
import ru.practicum.ewm_ms.dto.event.EventShortDto;
import ru.practicum.ewm_ms.exception.NotFoundException;
import ru.practicum.ewm_ms.model.Compilation;
import ru.practicum.ewm_ms.model.Event;
import ru.practicum.ewm_ms.repository.EventRepository;
import ru.practicum.ewm_ms.util.NotFoundMessageGen;

public class CompilationMapper {

    private CompilationMapper() {}

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

    private static Event[] replaceIdWithEvents(Long[] ids, EventRepository repo) {
        Event[] events = new Event[ids.length];

        for (int i = 0; i < ids.length; i++) {
            Event event = repo.findById(ids[i]).orElse(null);
            if (event == null) {
                throw new NotFoundException(NotFoundMessageGen.getEventNotFoundMessage(ids[i]));
            }
            events[i] = event;
        }
        return events;
    }

    private static EventShortDto[] replaceEventsWithEventShortDto(Event[] events) {
        EventShortDto[] dtos = new EventShortDto[events.length];

        for (int i = 0; i < events.length; i++) {
            dtos[i] = EventMapper.toEventShortDto(events[i]);
        }
        return dtos;
    }
}