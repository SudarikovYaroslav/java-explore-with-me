package ru.practicum.ewm_ms.dto.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;
import ru.practicum.ewm_ms.model.Location;
import ru.practicum.ewm_ms.util.CommonValidMarker;

@Getter
@Setter
@Validated
@AllArgsConstructor
public class EventPostDto {
    public static final int MIN_ANNOTATION_LEN = 20;
    public static final int MAX_ANNOTATION_LEN = 2000;
    public static final int MIN_DESCRIPTION_LEN = 20;
    public static final int MAX_DESCRIPTION_LEN = 7000;
    public static final int MIN_TITLE_LEN = 3;
    public static final int MAX_TITLE_LEN = 120;

    @Length(min = MIN_ANNOTATION_LEN, max = MAX_ANNOTATION_LEN, groups = CommonValidMarker.class)
    private String annotation;
    private Long category;
    @Length(min = MIN_DESCRIPTION_LEN, max = MAX_DESCRIPTION_LEN, groups = CommonValidMarker.class)
    private String description;
    private String eventDate;
    private Location location;
    private Boolean paid;
    private Integer participantLimit;
    private Boolean requestModeration;
    @Length(min = MIN_TITLE_LEN, max = MAX_TITLE_LEN, groups = CommonValidMarker.class)
    private String title;

    @Override
    public String toString() {
        return "EventPostDto{" +
                "annotation='" + annotation + '\'' +
                ", category=" + category +
                ", description='" + description + '\'' +
                ", eventDate='" + eventDate + '\'' +
                ", location=" + location +
                ", paid=" + paid +
                ", participantLimit=" + participantLimit +
                ", requestModeration=" + requestModeration +
                ", title='" + title + '\'' +
                '}';
    }
}
