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
    private long id;
    private boolean pinned;
    private String title;
}
