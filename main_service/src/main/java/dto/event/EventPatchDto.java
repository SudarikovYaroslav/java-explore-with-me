package dto.event;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class EventPatchDto {
    private String annotation;
    private Long category;
    private String description;
    private String eventDate;
    private Long id;
    private Boolean paid;
    private Integer participantLimit;
    private String title;
}
