package model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AdminUpdateEventRequest {
    private String annotation;
    private int category;
    private String description;
    private String eventDate; // в формате "yyyy-MM-dd HH:mm:ss"
    private Location location;
    private boolean paid;
    private int participantLimit;
    private boolean requestModeration;
    private String title;
}
