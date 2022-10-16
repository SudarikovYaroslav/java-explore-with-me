package main_service_application.dto.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
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
