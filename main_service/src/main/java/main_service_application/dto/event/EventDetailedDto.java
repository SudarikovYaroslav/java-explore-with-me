package main_service_application.dto.event;

import lombok.AllArgsConstructor;
import main_service_application.dto.category.CategoryDto;
import main_service_application.dto.user.UserShortDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import main_service_application.model.Location;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class EventDetailedDto {
    private String annotation;
    private CategoryDto category;
    private Integer confirmedRequests;
    private String createdOn;
    private String description;
    private String eventDate;
    private Long id;
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
