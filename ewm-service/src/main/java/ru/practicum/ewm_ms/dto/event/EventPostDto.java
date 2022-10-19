package ru.practicum.ewm_ms.dto.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.practicum.ewm_ms.model.Location;

@Getter
@Setter
@AllArgsConstructor
public class EventPostDto {
    private String annotation;
    private Long category;
    private String description;
    private String eventDate;
    private Location location;
    private Boolean paid;
    private Integer participantLimit;
    private Boolean requestModeration;
    private String title;
}
