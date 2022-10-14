package model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AdminUpdateEventRequest {
    private String annotation;
    private Integer category;
    private String description;
    private String eventDate; // в формате "yyyy-MM-dd HH:mm:ss"
    private Location location;
    private Boolean paid;
    private Integer participantLimit;
    private Boolean requestModeration;
    private String title;
}
