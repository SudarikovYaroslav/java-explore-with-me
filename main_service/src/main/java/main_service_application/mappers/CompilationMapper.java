package main_service_application.mappers;

import main_service_application.dto.compilation.CompilationPostDto;
import main_service_application.dto.compilation.CompilationResponseDto;
import main_service_application.dto.event.EventShortDto;
import main_service_application.exception.NotFoundException;
import main_service_application.model.Compilation;
import main_service_application.model.Event;
import main_service_application.repository.EventRepository;

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
                throw new NotFoundException("Не найден Event с id: " + ids[i]);
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