package dto.event;

import dto.CategoryDto;
import dto.user.UserShortDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import model.Location;

@Getter
@Setter
@RequiredArgsConstructor
public class EventDetailedDto {
    private String annotation;
    private CategoryDto category;
    private int confirmedRequests;
    private String createdOn;
    private String description;
    private String eventDate;
    private int id;
    private UserShortDto initiator;
    private Location location;
    private boolean paid;
    private int participantLimit;
    private String publishedOn;
    private boolean requestModeration;
    private String state;
    private String title;
    private long views;
}
