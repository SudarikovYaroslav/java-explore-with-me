package mappers;

import dto.compilation.CompilationPostDto;
import dto.compilation.CompilationResponseDto;
import dto.event.EventShortDto;
import exception.NotFoundException;
import model.Compilation;
import model.Event;
import repository.EventRepository;

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