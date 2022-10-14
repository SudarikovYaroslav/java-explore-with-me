package dto.compilation;

import dto.event.EventShortDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CompilationResponseDto {
    private EventShortDto[] events;
    private Long id;
    private Boolean pinned;
    private String title;
}
