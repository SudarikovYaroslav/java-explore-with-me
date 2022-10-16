package main_service_application.dto.compilation;

import lombok.AllArgsConstructor;
import main_service_application.dto.event.EventShortDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

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
