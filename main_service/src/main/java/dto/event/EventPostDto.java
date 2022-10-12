package dto.event;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import model.Location;

@Getter
@Setter
@RequiredArgsConstructor
public class EventPostDto {
    private String annotation;
    private int category;
    private String description;
    private String eventDate;
    private Location location;
    private boolean paid;
    private int participantLimit;
    private boolean requestModeration;
    private String title;
}
