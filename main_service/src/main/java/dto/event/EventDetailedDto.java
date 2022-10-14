package dto.event;

import dto.category.CategoryDto;
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
    private Integer confirmedRequests;
    private String createdOn;
    private String description;
    private String eventDate;
    private Integer id;
    private UserShortDto initiator;
    private Location location;
    private Boolean paid;
    private Integer participantLimit;
    private String publishedOn;
    private Boolean requestModeration;
    private String state;
    private String title;
    private Long views;
}
