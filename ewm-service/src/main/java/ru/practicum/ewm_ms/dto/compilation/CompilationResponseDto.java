package ru.practicum.ewm_ms.dto.compilation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import ru.practicum.ewm_ms.dto.event.EventShortDto;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class CompilationResponseDto {
    private EventShortDto[] events;
    private Long id;
    private Boolean pinned;
    private String title;
}
