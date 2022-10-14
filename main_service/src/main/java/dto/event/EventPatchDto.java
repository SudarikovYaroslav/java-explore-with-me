package dto.event;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class EventPatchDto {
    private String annotation;
    private Integer category;
    private String description;
    private String eventDate;
    private Long eventId;
    private Boolean paid;
    private Integer participantLimit;
    private String title;
}
