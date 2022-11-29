package ru.practicum.ms.mappers;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import ru.practicum.ms.dto.compilation.CompilationPostDto;
import ru.practicum.ms.dto.compilation.CompilationResponseDto;
import ru.practicum.ms.dto.event.EventShortDto;
import ru.practicum.ms.model.Compilation;
import ru.practicum.ms.model.Event;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class CompilationMapper {

    public static Compilation toModel(CompilationPostDto dto, List<Event> events) {
        return Compilation.builder()
                .events(events)
                .pinned(dto.getPinned())
                .title(dto.getTitle())
                .build();
    }

    public static CompilationResponseDto toResponseDto(Compilation compilation, List<EventShortDto> eventsDto) {
        return CompilationResponseDto.builder()
                .events(eventsDto)
                .id(compilation.getId())
                .pinned(compilation.getPinned())
                .title(compilation.getTitle())
                .build();
    }
}